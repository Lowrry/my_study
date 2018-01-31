package executors;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/21<p/>
 * Time: 11:34<p/>
 */
public class HappenBefore {

    static boolean ready;
    static volatile int number;

    private static class ReaderThread1 extends Thread {

        public void run() {
            number = 1;
        }
    }

    private static class ReaderThread2 extends Thread {
        public void run() {
//            if (ready) {
                System.out.println("==>" + number);
//            }
        }
    }

    public static void main(String[] para) throws InterruptedException {

        new ReaderThread1().start();
//        ready = true;
        Thread.sleep(1);
        new ReaderThread2().start();
    }

}