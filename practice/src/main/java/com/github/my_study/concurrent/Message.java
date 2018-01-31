package com.github.my_study.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 目前的分销系统,外部用户的流量并不是很大, 很多压力都是内部的消息带来的.
 * 很多时候需要对消息做去重再消费. 现在的很多去重逻辑都是先入库, 然后定时批量拉取出来, 消息去重再消费.
 *
 * 这种方案有几个问题:
 * 1. 大量消息出入库, 对数据库有压力的.
 * 2. 大量的消息入库, 会产生很多的数据, 占用磁盘空间
 * 3. 消息量特别大时, 表历史数据增多, 会出现慢查询.
 * 4. 消息量特别大时, 这种依赖表的方案, 吞吐量有限.
 *
 * User: luxiaochun<p/>
 * Date: 2018/1/12<p/>
 * Time: 19:16<p/>
 */
// dubbo没有用对象还是有他的道理的,不需要不停的创建对象和回收对象.就用的是基础数据类型
// 我这里也要改成基础数据类型int型. 日期其实就是一个int.
// todo 比较下数组和对象,哪个快,gc情况怎么样?
// todo 抽象出去重的条件, 可以自定义对象?
public class Message {

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 独占锁
    private final ReentrantReadWriteLock.WriteLock exclusiveLock = readWriteLock.writeLock();
    // 共享锁
    private final ReentrantReadWriteLock.ReadLock shareLock = readWriteLock.readLock();

    Long id;
    // 这里是根据开始日期和结束日期进行消息合并
    String begin;
    String end;

    public Message(Long id) {
        this.id = id;
        begin = null;
        end = null;
    }

    public Message merge(Message old) {
        if (old.begin != null) {
            this.setBegin(min(old.begin, this.begin));
        }
        if (old.end != null) {
            this.setEnd(max(old.end, this.end));
        }
        return this;
    }

    private String min(String a, String b) {
        if (a.compareTo(b) < 0) {
            return a;
        }
        return b;
    }

    private String max(String a, String b) {
        if (a.compareTo(b) > 0) {
            return a;
        }
        return b;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public ReentrantReadWriteLock.WriteLock exclusiveLock() {
        return exclusiveLock;
    }

    public ReentrantReadWriteLock.ReadLock shareLock() {
        return shareLock;
    }
}
