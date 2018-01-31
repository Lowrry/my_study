package concurrent.threads;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: luxiaochun<p/>
 * Date: 2017/12/12<p/>
 * Time: 13:38<p/>
 */
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am living ... ");
            }
        }, 1, 1, TimeUnit.SECONDS);

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am still living ... ");
            }
        }, 10, TimeUnit.SECONDS);

        Thread.sleep(3000);
        executor.shutdown();
        System.out.println("shut down ...");

    }

}
