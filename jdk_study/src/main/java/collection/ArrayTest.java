package collection;

import java.util.Arrays;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/22<p/>
 * Time: 18:55<p/>
 */
public class ArrayTest {

    public static void main(String[] args) {
        List list = Arrays.asList("1","2","3");
        String[] s = new String[1];

        Object[] objects = list.toArray(s);

        for (Object o : objects) {
            System.out.print(o);
        }
        System.out.println("");

        for (Object o : s) {
            System.out.print(o);
        }
        System.out.println("");

        System.out.println(list);
        System.out.println("success");
    }

}
