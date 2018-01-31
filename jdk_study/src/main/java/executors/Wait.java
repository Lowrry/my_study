package executors;

import java.util.HashMap;
import java.util.Map;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/21<p/>
 * Time: 10:35<p/>
 */
public class Wait {

    private final Object obj = this;

    boolean flag = true;
    Map<String, String> map = new HashMap<String, String>();

    //1.wait
    public void put() throws InterruptedException {
        while (!flag) {
            System.out.println("put flag = " + flag);
            System.out.println("i am put wait ...");

            synchronized (obj) {
                wait();
            }
        }
        map.put("put", "success");
        System.out.println(" put success");

//        Thread.sleep(1000);
        flag = false;

        synchronized (obj) {
            notifyAll();
        }
        System.out.println("put notify");
    }

    //2.notify
    public void take() throws InterruptedException {
        while (flag) {
            System.out.println("take flag = " + flag);
            System.out.println("i am take wait ...");

            synchronized (obj) {
                wait();
            }
        }
        map.put("take", "success");
        System.out.println("take success");

//        Thread.sleep(1000);
        flag = true;

        synchronized (obj) {
            notifyAll();
        }
        System.out.println("take notify");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main begin ...");

        final Wait wait = new Wait();

        new Thread(new Runnable() {
            public void run() {
                try {
                    wait.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            public void run() {
                try {
                    wait.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
