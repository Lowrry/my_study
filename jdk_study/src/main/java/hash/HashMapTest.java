package hash;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/30<p/>
 * Time: 15:55<p/>
 */
public class HashMapTest {

    private static Map<Integer, Integer> map = new HashMap<>();

    // 测试线程不安全问题
    @Test
    public void testMultiThreads() {

        final int NUM = 30;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM);
        final CountDownLatch latch = new CountDownLatch(NUM);


        for (int i = 0; i < NUM; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("i arrived");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("i am passed");

                    if (map != null) {
                        latch.countDown();
                        return;
                    }
                    System.out.println("map is null !");
                    map = new HashMap<>();

                    for (int j = 0; j < 100; j++) {
                        map.put(j, j);
                    }
                    for (int j = 0; j < 100; j++) {
                        map.get(j);
                    }
                    latch.countDown();
                }
            }, "MultiThread-" + i).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(map);
        System.out.println(map.entrySet().size());

        System.out.println("====================");

        for (int i = 0; i < 100; i++) {
            if (map.get(i) == null) {
                System.out.println(i);
            }
        }
    }


    @Test
    public void testInfiniteLoop() throws IOException {

        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }
                System.out.println("t1 over");
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t2 over");
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t3 over");
            }
        };

        Thread t4 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t4 over");
            }
        };

        Thread t5 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t5 over");
            }
        };

        Thread t6 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t6 over");
            }
        };

        Thread t7 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t7 over");
            }
        };

        Thread t8 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t8 over");
            }
        };

        Thread t9 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t9 over");
            }
        };

        Thread t10 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t10 over");
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        System.in.read();
    }

}

