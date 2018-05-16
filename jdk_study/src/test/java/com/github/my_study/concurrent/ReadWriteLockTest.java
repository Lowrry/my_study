package com.github.my_study.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: luxiaochun<p/>
 * Date: 2018/4/5<p/>
 * Time: 9:53<p/>
 */
public class ReadWriteLockTest {

    @Test
    public void testUpgradeLock(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        readLock.lock();
        try{
            System.out.println("get read lock");

            writeLock.lock();
            try{
                System.out.println("get write lock");
            }finally {
                writeLock.unlock();
            }
        }finally {
            readLock.unlock();
        }

        System.out.println("End");
    }

}
