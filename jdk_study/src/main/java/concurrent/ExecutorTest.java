package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/3<p/>
 * Time: 9:52<p/>
 */
public class ExecutorTest {

    public static void main(String[] args) {

        final AtomicInteger count = new AtomicInteger();

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(3, 3, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        r.run();
                    }
                });

        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.submit(new Runnable() {
                public void run() {
                    try {
                        int currentCount = count.getAndIncrement();
//                        System.out.println("Test Thread print " + currentCount);
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("Test Thread success " + currentCount);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        System.out.println("Total finished ... ");

    }

}
