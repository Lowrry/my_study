package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/2<p/>
 * Time: 18:00<p/>
 */
public class FutureTest {

    public synchronized void print1() throws InterruptedException {
        System.out.println("print1 ...");
        TimeUnit.SECONDS.sleep(100);
//        wait();
        System.out.println("print1 ,,,");
    }

    public synchronized void print2(){
        System.out.println("print2 ...");
        System.out.println("print2 ,,,");
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        final FutureTest test = new FutureTest();

        new Thread(new Runnable() {
            public void run() {
                try {
                    test.print1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                test.print2();
            }
        }).start();

    }


}
