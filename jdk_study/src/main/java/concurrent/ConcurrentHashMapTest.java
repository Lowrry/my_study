package concurrent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/4<p/>
 * Time: 17:53<p/>
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) throws InterruptedException {
        final Map<Date, String> map1 = Collections.synchronizedMap(new HashMap<Date, String>());
        final Map<Date, String> map2 = new ConcurrentHashMap<Date, String>(4);

        int totalThreadNum = 16;

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch latch1 = new CountDownLatch(totalThreadNum);
        final CountDownLatch latch2 = new CountDownLatch(totalThreadNum);

        Calendar instance = Calendar.getInstance();
        final List<Date> dates = new ArrayList<Date>();
        Date time = instance.getTime();
        for (int i = 0; i < 100000; i++) {
            dates.add(time);
            instance.add(Calendar.DATE, 1);
            time = instance.getTime();
        }

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < totalThreadNum; i++) {
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < 100000; j++) {
                                map1.put(dates.get(j), "1");
                            }
                            latch1.countDown();
                        }
                    }
            );
        }
        latch1.await();
        System.out.println("use time1 = " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < totalThreadNum; i++) {
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < 100000; j++) {
                                map2.put(dates.get(j), "2");
                            }
                            latch2.countDown();
                        }
                    }
            );
        }
        latch2.await();
        System.out.println("use time2 = " + (System.currentTimeMillis() - start2));

        System.out.println("map1 size = " + map1.size());
        System.out.println("map2 size = " + map2.size());

    }

}
