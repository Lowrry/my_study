package com.github.my_study.concurrent;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: luxiaochun<p/>
 * Date: 2018/3/29<p/>
 * Time: 14:12<p/>
 */
public class CyclicBarrierTest {

    @Test
    public void testRunnerOrder() throws IOException {
        CyclicBarrier barrier = new CyclicBarrier(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread come in : " + Thread.currentThread().getName());

                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Thread come out : " + Thread.currentThread().getName());
                }
            }, "" + i).start();
        }

        System.in.read();
    }

    @Test
    public void testLockOrder() throws IOException {
        ReentrantLock lock = new ReentrantLock();

        // 用一个barrier,让所有的线程一起开始?

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 这里让线程按顺序到达, 然后竞争lock, 都进入同步队列.
                    try {
                        Thread.sleep(finalI * 100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("before lock : " + Thread.currentThread().getName());
                    lock.lock();
                    try {
                        // 这里是看被唤醒,并且竞争到锁的线程,是不是按进入同步队列的顺序来的.
                        System.out.println("after lock : " + Thread.currentThread().getName());

                        if (finalI == 0) {
                            // 等待其他所有线程都竞争锁, 进入了同步队列里
                            try {
                                Thread.sleep(3000);
                                System.out.println("--------------------------");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    } finally {
                        lock.unlock();
                    }
                }
            }, i + "").start();
        }

        System.in.read();
    }


    // 测试从等待队列中唤醒,是不是也是顺序的?
    @Test
    public void testConditionSignalOrder() throws InterruptedException, IOException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " come in.");
                try {
                    lock.lock();
                    try {
                        condition.await();
                        System.out.println(Thread.currentThread().getName() + " come out.");
                    } finally {
                        lock.unlock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i).start();
            Thread.sleep(100);
        }

        System.out.println();
        System.out.println("-----------------");


//        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
//                condition.signal();
                condition.signalAll();
            } finally {
                lock.unlock();
            }
//        }

        System.in.read();
    }

    // 测试object的notify顺序

}
