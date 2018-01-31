package concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/10<p/>
 * Time: 14:01<p/>
 */
public class LockSupportTest {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("i am waiting ... ");
                LockSupport.park(new Object());
                System.out.println("i am running ... ");
            }
        });

        thread.start();

//        LockSupport.unpark(thread);
    }

}
