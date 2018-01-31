package collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/18<p/>
 * Time: 13:52<p/>
 */
public class SimpleListTest {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<Long>();

        long start = System.currentTimeMillis();
        for (Long i = 0L; i < 100000; i++) {
            list.add(0, i);
        }
        System.out.println("use time = " + (System.currentTimeMillis() - start));

        List<Long> list2 = new LinkedList<Long>();
        long start2 = System.currentTimeMillis();
        for (Long i = 0L; i < 100000; i++) {
            list2.add(0, i);
        }
        System.out.println("use time2 = " + (System.currentTimeMillis() - start2));
    }

}
