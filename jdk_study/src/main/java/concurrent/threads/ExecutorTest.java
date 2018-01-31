package concurrent.threads;

import java.util.concurrent.*;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/29<p/>
 * Time: 17:12<p/>
 */
public class ExecutorTest {

    public static void main(String[] args) {
        new ExecutorTest().newExecutor();

        System.out.println("finish ... ");
    }

    private void newExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        executor.allowCoreThreadTimeOut(true);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<5; i++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("i am running ...");
                }
            }
        });
    }

}
