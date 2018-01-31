package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/18<p/>
 * Time: 15:41<p/>
 */
public class CurrentMapTest {

    public static void main(String[] args) throws InterruptedException {
        final Map<Long, String> map = new HashMap<Long, String>();

        final Random random = new Random();
        final CountDownLatch countDown = new CountDownLatch(3);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            new Thread(
                    new Runnable() {
                        public void run() {
                            for (int j = 0; j < 10000; j++)
                                map.put(1L, "Test");
                            countDown.countDown();
                        }
                    }
            ).start();
        }

        countDown.await();
        System.out.println("use time = " + (System.currentTimeMillis() - start));
    }

}

