package concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/5<p/>
 * Time: 17:16<p/>
 */
public class AtomicReferenceTest {

    @Test
    public void test1() throws InterruptedException {
        AtomicReference<Long[]> reference = new AtomicReference<>(new Long[3]);

        for (int i = 0; i < 3; i++) {

            Long[] longs = reference.get();

//        User user1 = new User();

            longs[0] = 0L;

            System.out.println("new user = " + longs);

            boolean b = reference.compareAndSet(longs, longs);
            System.out.println("put ==> " + b);

            System.out.println(reference.get());

            Thread.sleep(10);
        }
    }

    static class User {
        List<Long> ids = new ArrayList<>();

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("ids=").append(ids);
            sb.append('}');
            return sb.toString();
        }
    }

    @Test
    public void test2() throws InterruptedException {

        List<String> set = new ArrayList<>();
        AtomicReference<List<String>> atomiSet = new AtomicReference<>(set);

        int NUM = 100;
        CountDownLatch countDownLatch = new CountDownLatch(NUM);

        for (int i = 0; i < NUM; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (; ; ) {
                        List<String> currentSet = atomiSet.get();
                        List<String> newSet = new ArrayList<String>(currentSet);
                        newSet.add(finalI + "");

                        if (atomiSet.compareAndSet(currentSet, newSet)) {
                            break;
                        }
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();

        List<String> result = atomiSet.get();
        System.out.println(result);
        System.out.println("**********");

        for (int i = 0; i < NUM; i++) {
            if (!result.contains(i + "")) {
                System.out.println("not has " + i);
            }
        }


    }

    @Test
    public void testInitCas() throws InterruptedException {


        final AtomicReference<long[]> atomicList = new AtomicReference<>();

//        if (reference == null) {
//            statisticsMap.putIfAbsent(statistics, new AtomicReference<long[]>());
//            reference = statisticsMap.get(statistics);
//        }

        CountDownLatch countDownLatch = new CountDownLatch(10);

        int NUM = 20;
        for (int i = 0; i < NUM; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (; ; ) {
                        long[] currentList = atomicList.get();

                        long[] newList = new long[10];

                        if (currentList != null) {
                            for (int i = 0; i < 10; i++) {
                                newList[i] = currentList[i] + 1;
                            }
                        } else {
                            newList = new long[]{1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l};
                        }

                        if (atomicList.compareAndSet(currentList, newList)) {
                            break;
                        }
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println("All Finished ...");

        long[] result = atomicList.get();

        List<Long> resultList = new ArrayList<>();
        for (long r : result) {
            resultList.add(r);
        }

        for (int i = 0; i < NUM; i++) {
            if (!resultList.contains(i)) {
                System.out.println("not has " + i);
            }
        }

        for (long l : atomicList.get()) {
            System.out.print(l);
            System.out.print(", ");
        }

    }

    @Test
    public void testCasResult() {
        AtomicReference<Object> reference = new AtomicReference<>();
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();

        reference.set(o1);
        System.out.println(o1 == reference.get());

        System.out.println(reference.compareAndSet(o1, o2));

        Object old = reference.get();

        System.out.println(old == o1);
        System.out.println(old == o2);

    }

}
