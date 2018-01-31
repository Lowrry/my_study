package concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: luxiaochun<p/>
 * Date: 2017/12/15<p/>
 * Time: 18:00<p/>
 */
public class BlockingQueueTest {

    //测试谁的吞吐量更高
    @org.junit.Test
    public void test() throws BrokenBarrierException, InterruptedException {
        final int QUEUE_SIZE = Integer.MAX_VALUE - 10;
        final int PRE_TASTS = 200000;

        // 测试20次结果
        for (int i = 0; i < 20; i++) {

            BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(QUEUE_SIZE);
            for (int j = 0; j < PRE_TASTS; j++) {
                blockingQueue.offer("1");
            }
            testBlockingQueue(blockingQueue);

            blockingQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
            for (int j = 0; j < PRE_TASTS; j++) {
                blockingQueue.offer("1");
            }
            testBlockingQueue(blockingQueue);

            System.out.println("==================================");
        }
    }

    public void testBlockingQueue(final BlockingQueue<String> blockingQueue) throws BrokenBarrierException, InterruptedException {

        // THREAD_NUM个生产者 和 THREAD_NUM个消费者线程
        // 目标是读和写任务都完成, 怎么精确描述任务完成呢?  用barrier!

        final int THREAD_NUM = 4;
        final int TASK_NUM = 100000;

        final AtomicLong finishOffers = new AtomicLong();

        final CountDownLatch latch = new CountDownLatch(1);

        final CyclicBarrier barrier = new CyclicBarrier(2 * THREAD_NUM + 1);

        //消费线程
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread pollThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (; ; ) {
                        try {
                            String result = blockingQueue.poll();

                            // 这里判断offer线程都已经结束,并且poll拿不到任务了
                            if (result == null && finishOffers.get() == 5) {
                                try {
                                    System.out.println("poll threads finished ...");
                                    barrier.await();
                                } catch (BrokenBarrierException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            pollThread.setName("PollThread-" + i);
            pollThread.start();
        }

        //生产者线程
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread offerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < TASK_NUM; j++) {
                        blockingQueue.offer("1");
                    }

                    System.out.println("offer threads finished ...");
                    finishOffers.incrementAndGet();

                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            offerThread.setName("OfferThread-" + i);
            offerThread.start();
        }

        long start = System.currentTimeMillis();
        //这个调用了,所有线程就都开始了
        latch.countDown();

        //这个方法结束,所有线程就都结束了
        barrier.await();

        System.out.println("use time => " + (System.currentTimeMillis() - start));

    }

}
