package executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/18<p/>
 * Time: 13:04<p/>
 */
public class ScheduleTest {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, Executors.defaultThreadFactory());

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("i am running ...");
                try {
                    System.out.println("start to sleep ...");
                    Thread.sleep(5000);
                    System.out.println("start to awke ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 3, 1 , TimeUnit.SECONDS);
    }

}
