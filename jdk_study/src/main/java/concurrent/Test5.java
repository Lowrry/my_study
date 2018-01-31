package concurrent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/15<p/>
 * Time: 14:52<p/>
 */
public class Test5 {

    public static void main(String[] args) throws IOException, InterruptedException {
        final AtomicInteger integer = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        final ReentrantLock lock = new ReentrantLock();
        for (int i = 1; i <= 20; i++) {
            executorService.execute(
                    new Runnable() {
                        public void run() {
                            try {
                                int num = integer.getAndIncrement();
                                Local.set(num + "", num);
                                boolean success = lock.tryLock(3, TimeUnit.SECONDS);
                                System.out.println("try lock -->" + success);
                                if (!success) return;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(200);
                                System.out.println("I am running");
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (lock.isHeldByCurrentThread())
                                    lock.unlock();
                                System.out.println("### current Local = " + Local.get());
                            }
                        }
                    }
            );
        }

        System.out.println(Local.get());
        Thread.sleep(20000);
        System.in.read();
    }

}

class Local {

    private static ThreadLocal<Map<String, Integer>> threadLocal = new ThreadLocal<Map<String, Integer>>() {
        @Override
        protected Map<String, Integer> initialValue() {
            return new HashMap<String, Integer>();
        }
    };

    public static void set(String s, Integer l) {
        threadLocal.get().put(s, l);
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static Map<String, Integer> get() {
        return threadLocal.get();
    }


}
