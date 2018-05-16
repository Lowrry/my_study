package string;

import org.junit.Test;

/**
 * User: luxiaochun<p/>
 * Date: 2018/3/1<p/>
 * Time: 14:30<p/>
 */
public class StringBuilderTest {

    @Test
    public void test1(){
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.length());

        sb.append("333");
        System.out.println(sb.length());
    }

}
