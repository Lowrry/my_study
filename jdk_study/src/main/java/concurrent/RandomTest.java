package concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/4<p/>
 * Time: 17:26<p/>
 */
public class RandomTest {

    public static void main(String[] args) {
        final Random random = new Random();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(random.nextInt());
                }
            });
        }

    }

}
