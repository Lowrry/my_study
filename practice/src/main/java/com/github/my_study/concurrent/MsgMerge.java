package com.github.my_study.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/12<p/>
 * Time: 14:04<p/>
 */
public class MsgMerge {

    // 对各种类型的数据进行合并后处理
    // 每隔一段时间提交一次
    // 或每隔一段时间提交一次

    // 某种消息,第一次进来会立马执行
    // 主要应对canal消息,进行合并后处理

    // 设计目标在并发写入的时候, 可以定时cas的合并消息,然后提交;
    // 消息不会源源不断的来的, 如果尝试几次失败了的话, 就强制加锁提交消息. 类似于ConcurrentHashMap
    // 高性能的系统,很多结构都要自己定制的.
    // 供应商那边会有价格消息定时导入 以及 汇率商品定时批量更新的问题

    // 上次我的设计是想利用读写锁?
    // 还有想利用cas的工具类,进行cas的修改,避免加锁.

    // todo getAndUpdate
    // segment数和线程数关系的影响也可以测试下. 这应该是个相对固定的值,因为lts的线程数是可控的.
    private final ConcurrentHashMap<Long, AtomicReference<Message>> currentMap = new ConcurrentHashMap<>();  // AtomicReference本身就是一个原子引用,可以cas修改.

    // 继承读写锁,是想对对象整体加锁

    // 这个send动作,其实就是插入的意思,就是都是增量的消息.

    // 更多的利用cas而不是锁,两种方案一起比较下看看呢.
    // 这个可以写个Blog. 比较下不同方案的处理量.
    // 还有关于价格多级缓存的,后面也要做一下,不一定要上线,但是要实现出来.
    // 没有消息的,会有一个线程主动扫描一次.判断消息的上次发送时间,先CAS更新时间,再发送一次.
    // 在发送消息的时候,先对AtomicReference.
    public void receive(Message message) {
        Long key = message.getId();


        AtomicReference<Message> reference = currentMap.get(key);

        // 根据是否为null,判断是否包含key.因为ConcurrentHashMap的key,value都不允许为null.
        if (reference == null) {
            // 不包含key,这里初始化AtomicReference
            AtomicReference<Message> atomicReference = new AtomicReference<>();
            atomicReference.set(message);

            AtomicReference<Message> returnObject = currentMap.putIfAbsent(key, atomicReference);
            // 如果put成功,返回结果就是null
            if (returnObject == null) {
                reference = atomicReference;
            } else {    // 如果返回不为null,说明其他线程已经放入成功了.
                reference = returnObject;
            }
        }

        for (; ; ) {
            //todo 获取共享锁

            Message current = reference.get();
            /*
             * 因为receive方法,只是插入动作,没有删除动作
             * 这里判断时间在已有区间内的,方法直接结束
             */
            if (message.getBegin().compareTo(current.getBegin()) >= 0           // 大于等于开始时间
                    && message.getEnd().compareTo(current.getEnd()) <= 0) {     // 并且小于等于结束时间
                return;
            }

            Message newMsg = message.merge(current);            // 合并时间段

            if (reference.compareAndSet(current, newMsg)) {     // cas更新
                // todo 需要限制cas次数吗? 没必要吧, 某个商品只是会有短暂的并发消息, 不会一直有的.
                // todo 达到次数立马提交一次.
                // todo 对并发小的消息,做立马提交,这样低并发的消息不会进入合并的这个动作,直接穿透.
                // 怎么控制同一个商品消息,会分发到同一台机器上呢? 用不同的机器,响应不同的消息类型.
                // 每个客户端先合并消息,再提交一次到数据库进行再合并后,再处理消息.

                return;
            }
        }
    }

    // 定时提交或者到一定的数量就提交一次
    // 这里是定时提交一次
    public void totalSend() {
        // 这里进行线程安全的迭代
        // 重点是这个只是插入动作,所以只会增加,不会减少. 有消息发送到一定的数量或者到一定的时间就主动发送一次.
        // 迭代器
        for (Map.Entry<Long, AtomicReference<Message>> entry : currentMap.entrySet()) {

            Long key = entry.getKey();
            AtomicReference<Message> reference = entry.getValue();
            Message current = reference.get();  // 这里current引用get()的结果,这种直接引用不是线程安全的.

            // 将价格置空, 这个不行,这里要清理掉的
//                if (reference.compareAndSet(current, new Message(key))) {
//                    break;
//                }

            // todo 锁的粒度尽量小, 清理某个key时, 在本地对String加锁, 这样就可以清理对应的key了.
            // todo 继承读写锁, 来实现排他锁, 然后看看每个对象的大小如何. 同一时间不能操作同一个id就行了.
            // 这里使用读写锁,作为共享锁和排他锁使用.
            AtomicReference<Message> messageReference = currentMap.get(key);

            // 这里主要是用来做共享锁和独占锁来使用的
            // 往里面添加数据,是用的cas,因为只会新增,所以cas没有aba的风险
            // 删除对象也可以使用cas吧? 不行? 需要删除对象
            // 是否也可以将开始结束时间更新为null呢? 这样下次就没有数据要更新了, 就是更新为null, 也是用cas.
            // 但是这样有个不好的情况,就是会有残留的对象, 怎么清除呢?
            // 还是最好在提交的时候, 对每个id对象进行加锁, 然后进行清理.
            // 这个锁, 当然最好最好是加在每个对象上了, 这样就没有全局的锁

            ReentrantReadWriteLock.WriteLock exclusiveLock = messageReference.get().exclusiveLock();

            // todo try lock 和 正常的lock区别?
            // try lock是用于循环尝试加锁的,加锁成功就会返回true
            // lock是阻塞等待获取锁
            // todo trylock和直接lock,比较下哪种吞吐量大些?
            // todo 公平和非公平也比较下,在高并发下,公平的是不是比非公平的吞吐量更高些?
            exclusiveLock.lock();
            try {
                // 提交消息, 其实在锁里最好不做远程操作, 不然容易被拖累, 这里做什么操作比较好呢.
                // 这里做一个尝试提交到线程池的动作,如果提交失败,就不做修改,等下次定时任务.
                // 这里的remove必须要在排他锁的保护下操作,防止漏提交.
                // todo 这里要删除吗? 要删除的,否则这个map里对象会不会越来越大呢?
                currentMap.remove(key);
            } finally {
                exclusiveLock.unlock();
            }

            // 其实这里需要用到共享锁和排他锁, 这个怎么使用呢
        }
        // 只是对每个id进行加锁.
        // 比较下全局加锁和key字段加锁的性能差距
        // 走到这里说明更新成功了,提交消息
    }
}


/**
 * 1. 测试下正确性
 * 2. 先测试下当前工具类的处理能力.
 * 3. 这个不行就年后上线, 看看效果, 也做个开关, 看看效果
 * 4. 测试的时候,去重策略最好改成入list
 * 5. 测试下读写锁,是不是写锁释放了,所有的读锁就都打开了
 *
 * // todo, 1. 测试下几十万的消息, 在内存里占用内存会有多大??
 */
