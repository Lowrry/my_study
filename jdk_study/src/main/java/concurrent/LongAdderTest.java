package concurrent;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/15<p/>
 * Time: 20:12<p/>
 */
public class LongAdderTest {

    public static void main(String[] args) throws InterruptedException {

//        final LongAdder longAdder = new LongAdder();
//        final AtomicLong atomicLong = new AtomicLong();
//
//        int NUM = 100;
//        int TOTAL = 999999999;
//
//        long start1 = System.currentTimeMillis();
//        CountDownLatch countDownLatch1 = new CountDownLatch(NUM);
//        for(int i=0; i<NUM; i++){
//            new Thread(
//                    () -> {
//                        for(int j=0;j<TOTAL;j++){
//                            longAdder.add(1);
//                            countDownLatch1.countDown();
//                        }
//                    }
//            ).start();
//        }
//        countDownLatch1.await();
//        System.out.println("use time1 = " + (System.currentTimeMillis() - start1));
//
//        long start2 = System.currentTimeMillis();
//        CountDownLatch countDownLatch2 = new CountDownLatch(NUM);
//        for(int i=0; i<NUM; i++){
//            new Thread(
//                    () -> {
//                        for(int j=0;j<TOTAL;j++){
//                            atomicLong.addAndGet(1);
//                            countDownLatch2.countDown();
//                        }
//                    }
//            ).start();
//        }
//        countDownLatch2.await();
//        System.out.println("use time2 = " + (System.currentTimeMillis() - start2));


    }

}
