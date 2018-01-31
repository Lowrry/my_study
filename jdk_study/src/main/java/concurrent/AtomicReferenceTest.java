package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/5<p/>
 * Time: 17:16<p/>
 */
public class AtomicReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Long[]> reference = new AtomicReference<>(new Long[3]);

        for (int i = 0; i < 3; i++) {

            Long[] longs = reference.get();

//        User user1 = new User();

            longs[0] = 0L;

            System.out.println("new user = " + longs);

            boolean b = reference.compareAndSet(longs, longs);
            System.out.println("put ==> " + b);

            System.out.println(reference.get());

            Thread.sleep(10);
        }
    }

    static class User {
        List<Long> ids = new ArrayList<>();

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("ids=").append(ids);
            sb.append('}');
            return sb.toString();
        }
    }

}
