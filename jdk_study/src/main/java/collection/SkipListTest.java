package collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/7<p/>
 * Time: 15:48<p/>
 */
public class SkipListTest {

    public static void main(String[] args) {
        Set<Long> set = new ConcurrentSkipListSet<Long>();

        long start = System.currentTimeMillis();
        for (long i = 0l; i < 100000l; i++) {
            set.add(i);
        }
        System.out.println("use time = " + (System.currentTimeMillis() - start));

        List<Long> list2 = new LinkedList<Long>();
        long start2 = System.currentTimeMillis();
        for (Long i = 0L; i < 100000l; i++) {
            list2.add(0, i);
        }
        System.out.println("use time2 = " + (System.currentTimeMillis() - start2));
    }

}
