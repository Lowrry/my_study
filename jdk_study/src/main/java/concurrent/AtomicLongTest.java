package concurrent;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: luxiaochun<p/>
 * Date: 2018/3/15<p/>
 * Time: 15:26<p/>
 */
public class AtomicLongTest {

    @Test
    public void testTotalCount() throws IOException {
        AtomicLong al = new AtomicLong();

        long start = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("Total use time = " + (System.currentTimeMillis()-start));
            }
        });
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<1000000;j++){
                        al.incrementAndGet();
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
