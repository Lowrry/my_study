package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/10<p/>
 * Time: 13:56<p/>
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        countDownLatch.await();

        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.countDown();


    }

}
