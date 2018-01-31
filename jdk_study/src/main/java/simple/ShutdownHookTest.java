package simple;

import java.util.concurrent.TimeUnit;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/15<p/>
 * Time: 11:23<p/>
 */
public class ShutdownHookTest {

    public static void main(String[] args) {
        System.out.println("begin ...");

        Runtime.getRuntime().addShutdownHook(new Thread(
                new Runnable() {
                    public void run() {
                        System.out.println("I am sleeping ...");
                        try {
                            TimeUnit.SECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("I am awake again ...");
                    }
                }
        ));

        System.out.println("end ...");
    }
}
