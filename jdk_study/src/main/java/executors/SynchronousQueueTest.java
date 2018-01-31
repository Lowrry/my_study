package executors;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/12<p/>
 * Time: 17:01<p/>
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    LockSupport.parkNanos(this, 1000000000);
                    System.out.println("***************");
                }
            }
        });

        thread.start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(;;){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    LockSupport.unpark(thread);
//                }
//            }
//        }).start();


        System.in.read();
    }

}
