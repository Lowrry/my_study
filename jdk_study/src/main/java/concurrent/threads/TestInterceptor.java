package concurrent.threads;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/7<p/>
 * Time: 17:44<p/>
 */
public class TestInterceptor {

    public static void main(String[] args) {
        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        System.out.println("begin");
                        Thread.currentThread().interrupt();
                        System.out.println("end");
                    }
                }
        );

        thread.start();
        thread.interrupt();
    }

}
