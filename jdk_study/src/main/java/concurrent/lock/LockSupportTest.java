package concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/27<p/>
 * Time: 15:10<p/>
 */
public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        for(;;){
                            System.out.println("I am thread_1 ...");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            LockSupport.park();
                        }
                    }
                }
        );

        thread.start();

        Thread.sleep(3000);
        LockSupport.unpark(thread);
    }

}
