package concurrent.volatilee;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/29<p/>
 * Time: 16:10<p/>
 */
public class VolatileTest {

    static volatile String[] ss = new String[]{"1", "2", "3", "4", "5"};

    public static void main(String[] args) throws InterruptedException {

//        for (int i = 0; i < 100; i++) {
//            final CountDownLatch count = new CountDownLatch(1);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        ss[1] = "0";
                        System.out.println("update ... ");
//                            count.countDown();
                    }
                }
        ).start();

//            count.await();
//        Thread.sleep(1);
        for (String s : ss) {
            System.out.print(s);
        }
        System.out.println();
//        }
    }

}
