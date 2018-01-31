package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/27<p/>
 * Time: 10:04<p/>
 */
public class BarrierTest extends AbstractQueuedSynchronizer{


    public static void main(String[] args) {
        BarrierTest barrierTest = new BarrierTest();


        final AtomicBoolean open = new AtomicBoolean(true);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("All Threads is Finished");
            }
        });

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (open.get()) {
                        System.out.println(Thread.currentThread().getName() + " is arrived");
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName() + " is Finished");
                    }
                }
            }).start();
        }
    }

}
