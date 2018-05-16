package com.github.my_study.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/12<p/>
 * Time: 14:04<p/>
 */
public class MsgMerge {

    private final ConcurrentHashMap<Long, AtomicReference<Message>> currentMap = new ConcurrentHashMap<>();  // AtomicReference本身就是一个原子引用,可以Cas修改.

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                // todo 关闭容器的时候,全部提交一次
                // todo spring容器关闭时,停止接收消息?
            }
        }));
    }

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
            Message current = reference.get();
            /*
             * 因为receive方法,只是插入动作,没有删除动作
             * 这里判断时间在已有区间内的,方法直接结束
             */
            if (message.getBegin().compareTo(current.getBegin()) >= 0           // 大于等于开始时间
                    && message.getEnd().compareTo(current.getEnd()) <= 0) {     // 并且小于等于结束时间
                return;
            }

            // 获取共享锁
            current.shareLock().lock();
            try {
                Message newMsg = message.merge(current);            // 合并时间段

                if (reference.compareAndSet(current, newMsg)) {     // cas更新
                    // todo 需要限制cas次数吗? 没必要吧, 某个商品只是会有短暂的并发消息, 不会一直有的.
                    // todo 达到次数立马提交一次.
                    // todo 对并发小的消息,做立马提交,这样低并发的消息不会进入合并的这个动作,直接穿透.
                    // 怎么控制同一个商品消息,会分发到同一台机器上呢? 用不同的机器,响应不同的消息类型.
                    // 每个客户端先合并消息,再提交一次到数据库进行再合并后,再处理消息.

                    return;
                }
            } finally {
                current.shareLock().unlock();
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

            // 这里使用读写锁,作为共享锁和排他锁使用.
            AtomicReference<Message> messageReference = currentMap.get(key);

            Message message = messageReference.get();

            // todo try lock 和 正常的lock区别? 其实就是有没有出队和入队的区别.
            // try lock是用于循环尝试加锁的,加锁成功就会返回true
            // lock是阻塞等待获取锁
            // todo trylock和直接lock,比较下哪种吞吐量大些?
            // todo 公平和非公平也比较下,在高并发下,公平的是不是比非公平的吞吐量更高些?
            message.exclusiveLock().lock();
            try {
                // todo 提交消息, 其实在锁里最好不做远程操作, 不然容易被拖累, 这里做什么操作比较好呢.
                // 这里做一个尝试提交到线程池的动作,如果提交失败,就不做修改,等下次定时任务.
                // 这里的remove必须要在排他锁的保护下操作,防止漏提交.
                // todo 这里要删除吗? 要删除的,否则这个map里对象会不会越来越大呢?
                currentMap.remove(key);
            } finally {
                message.exclusiveLock().unlock();
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
 * <p>
 * // todo, 1. 测试下几十万的消息, 在内存里占用内存会有多大?? 可以看到内存占用的.
 */
