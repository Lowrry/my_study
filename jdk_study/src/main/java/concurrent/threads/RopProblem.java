package concurrent.threads;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/27<p/>
 * Time: 16:45<p/>
 */
public class RopProblem {

    //问题出在A线程池提交任务给B线程池,然后A等待B结果,再返回.
    //测试一下,在队列满了以后, 打印A接收的id, 和B返回的id, 看看是否一致.

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AtomicLong ato = new AtomicLong();

        final ThreadPoolExecutor executor1 = new ThreadPoolExecutor(200, Integer.MAX_VALUE, 10,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

        ScheduledThreadPoolExecutor executor2 = new ScheduledThreadPoolExecutor(1);

        executor2.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("active thread count = " + executor1.getActiveCount());
            }
        }, 1, 1, TimeUnit.SECONDS);


        for (int i = 0; i < 2000; i++) {
            final long sendId = ato.getAndIncrement();
            final Future<Long> future = executor1.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    Thread.sleep(2000);
                    return sendId;
                }
            });

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (future.get().equals(sendId)) {
                            System.out.println("error id => " + sendId + " , " + future.get());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
