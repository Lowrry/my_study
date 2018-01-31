package bigdecimal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/18<p/>
 * Time: 14:00<p/>
 */
public class IntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(Integer.MAX_VALUE);
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.decrementAndGet());
    }

}
