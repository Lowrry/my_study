package Jdk.jvmBlog;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: luxiaochun<p/>
 * Date: 2017/10/25<p/>
 * Time: 15:35<p/>
 */
public class ExecutorGc {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorGc executorGc = new ExecutorGc();
        executorGc.doCreateExecutor();

        Thread.sleep(1000);
        System.gc();
        System.in.read();
    }

    private void doCreateExecutor(){
        SimpleExecutor simpleExecutor = new SimpleExecutor(0,1,10, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
//        simpleExecutor.allowCoreThreadTimeOut(true);
        simpleExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("alive ...");
//                for(;;){
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("alive ...");
//                }
            }
        });
    }

    class SimpleExecutor extends ThreadPoolExecutor{

        public SimpleExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void finalize() {
            System.out.println("SimpleExecutor is being gc ... ");
            super.finalize();
        }
    }

}
