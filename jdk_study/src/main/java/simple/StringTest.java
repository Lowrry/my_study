package simple;

import org.junit.Test;

import java.util.Arrays;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/16<p/>
 * Time: 20:21<p/>
 */
public class StringTest {

    @Test
    public void test1() {
        Long long1 = new Long(100000);
        Long long2 = new Long(100000);

        String s1 = long1.toString().intern();
        String s2 = long2.toString().intern();

        System.out.println(s1 == s2);
    }

    @Test
    public void test2() {
        String[] days = new String[]{"20171211", "20171007", "20181007"};

        Arrays.sort(days);

        for (String day : days) {
            System.out.println(day);
        }
    }


}
