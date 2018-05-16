package concurrent.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: luxiaochun<p/>
 * Date: 2018/3/14<p/>
 * Time: 14:39<p/>
 */
public class TryLockTest {

    // 测试lock和tryLock吞吐量.
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(false);
        Object obj = new Object();

        final int THREAD_NUM = 50;
        final int TIMES = 1000;

        long start = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("use time = " + (System.currentTimeMillis() - start));

                synchronized (obj) {
                    obj.notifyAll();
                }
            }
        });


        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 测试tryLock
                    for (int j = 0; j < TIMES; j++) {
                        for (; ; ) {
                            if (lock.tryLock()) {
                                try {
                                    int a = 1 + 2;
                                    break;
                                } finally {
                                    lock.unlock();
                                }
                            }
                        }
                    }

//                    // 测试lock
//                    for (int j = 0; j < TIMES; j++) {
//                        lock.lock();
//                        try {
//                            int a = 1 + 2;
//                        } finally {
//                            lock.unlock();
//                        }
//                    }
//                    System.out.println("finished");

                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        synchronized (obj) {
            obj.wait();
        }
        System.out.println("===============================");
        cyclicBarrier.reset();


        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 测试Lock
                    for (int j = 0; j < TIMES; j++) {
                        lock.lock();
                        try {
                            int a = 1 + 2;
                        } finally {
                            lock.unlock();
                        }
                    }

//                    System.out.println("finished");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
