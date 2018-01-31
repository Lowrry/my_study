package google;

import google.limiter.RateLimiter;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/28<p/>
 * Time: 14:46<p/>
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(10);

        long now = System.currentTimeMillis();
        long number = 1;

        for (; ; ) {
            boolean success = limiter.tryAcquire(1);
            if (success)
                System.out.println(++number + " use time = " + (System.currentTimeMillis() - now));
        }
    }

}
