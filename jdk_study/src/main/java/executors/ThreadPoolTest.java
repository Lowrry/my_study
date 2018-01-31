package executors;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/7<p/>
 * Time: 14:46<p/>
 */
public class ThreadPoolTest {

    static ConcurrentMap<String, ExecutorService> map = new ConcurrentHashMap<String, ExecutorService>();

    static ExecutorService getInstance(String name){
        if(!map.containsKey(name)){
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            map.put(name, executorService);
            return executorService;
        }
        return map.get(name);
    }

    public static void main(String[] args) {

        getInstance("test").execute(
                new Runnable() {
                    public void run() {
                        System.out.println("testing ...");
                    }
                }
        );
        getInstance("test").shutdownNow();
        map.clear();
        getInstance("test").execute(
                new Runnable() {
                    public void run() {
                        System.out.println("testing ...");
                    }
                }
        );

    }

}
