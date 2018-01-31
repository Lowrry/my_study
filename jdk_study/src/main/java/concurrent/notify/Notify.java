package concurrent.notify;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/22<p/>
 * Time: 19:26<p/>
 */
public class Notify {

    private final Object obj = new Object();

    public void print(String name) throws InterruptedException {
        System.out.println("notify print begin...");

        synchronized (obj){
            obj.wait();
        }

        System.out.println("notify print end..." + name);
    }

    public static void main(String[] args) throws InterruptedException {
        final Notify notify = new Notify();

        new Thread(new Runnable() {
            public void run() {
                try {
                    notify.print("test1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    notify.print("test2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("sleeping ...");
        Thread.sleep(2000);

        new Thread(new Runnable() {
            public void run() {
                synchronized (notify.obj){
                    notify.obj.notifyAll();
                }
            }
        }).start();
    }

}
