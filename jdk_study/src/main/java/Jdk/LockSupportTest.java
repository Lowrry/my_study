package Jdk;

import java.util.concurrent.locks.LockSupport;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/7<p/>
 * Time: 19:50<p/>
 */
public class LockSupportTest {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Runnable() {
            private int count = 0;

            public void run() {
                long start = System.currentTimeMillis();
                long end = 0;

                while ((end - start) <= 1000) {
                    count++;
                    end = System.currentTimeMillis();
                }

                System.out.println("after 1 second.count=" + count);

                //等待或许许可
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });

        t.start();

        Thread.sleep(2000);

        // 中断线程
        t.interrupt();

        System.out.println("main over");
    }

}
