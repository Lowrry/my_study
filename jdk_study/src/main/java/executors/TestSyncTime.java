package executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/16<p/>
 * Time: 19:54<p/>
 */
public class TestSyncTime {

    ConcurrentMap<Long, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        final TestSyncTime testSyncTime = new TestSyncTime();

        int num = 3;

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final CountDownLatch countDownLatch1 = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                countDownLatch.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            testSyncTime.add(1L, "test");
                            countDownLatch1.countDown();
                        }
                    }
            ).start();
        }

        System.out.println("i am sleep ...");
        Thread.sleep(3000);

        long start = System.currentTimeMillis();
        countDownLatch.countDown();
        countDownLatch1.await();
        System.out.println("i am end ... use time = " + (System.currentTimeMillis() - start));
    }

    public synchronized void add(Long key, String value) {
        if(map.get(key) == null){
            map.put(key, new ArrayList<String>());
        }
        List<String> strings = map.get(key);
        strings.add(value);
        map.put(key, strings);
    }

}
