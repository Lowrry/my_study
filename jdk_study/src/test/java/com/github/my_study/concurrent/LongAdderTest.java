package com.github.my_study.concurrent;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.LongAdder;

/**
 * User: luxiaochun<p/>
 * Date: 2018/4/4<p/>
 * Time: 10:24<p/>
 */
public class LongAdderTest {

    @Test
    public void compareTime() throws IOException {
//        AtomicLong atomicLong = new AtomicLong(0);
        LongAdder atomicLong = new LongAdder();
        final int THREAD_NUM = 64;
        final int LOOP = 1000000;

        long start = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("Finally atomicLong = " + atomicLong.longValue() + ", use time = " + (System.currentTimeMillis() - start));
            }
        });
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < LOOP; j++) {
//                        atomicLong.incrementAndGet();
                        atomicLong.add(1L);
                    }

                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.in.read();
    }

}
