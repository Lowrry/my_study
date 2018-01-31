package concurrent;

import java.util.concurrent.*;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/4<p/>
 * Time: 17:13<p/>
 */
public class SkipListTest {

    public static void main(String[] args) throws InterruptedException {

        SkipListTest skipListTest = new SkipListTest();
        for (int i = 0; i < 10; i++) {
            skipListTest.test();
            System.out.println("*************");
        }

    }

    private void test() throws InterruptedException {
        final ConcurrentSkipListSet<String> set1 = new ConcurrentSkipListSet<String>();
//        final Set<String> set2 = Collections.synchronizedSet(new HashSet<String>());
//        final Set<String> set2 = new HashSet<String>();
        final ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<String, Boolean>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Thread.sleep(1000);

        int total = 30;

        final CountDownLatch latch1 = new CountDownLatch(total);
        final CountDownLatch latch2 = new CountDownLatch(total);

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < 10000; j++) {
                                set1.add("1");
                            }
                            latch1.countDown();
                        }
                    }
            );
        }
        latch1.await();
        System.out.println("use time1 = " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < 10000; j++) {
                                map.put("1", Boolean.TRUE);
                            }
                            latch2.countDown();
                        }
                    }
            );
        }
        latch2.await();
        System.out.println("use time2 = " + (System.currentTimeMillis() - start2));
    }

}
