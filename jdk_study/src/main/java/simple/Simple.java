package simple;

import org.junit.Test;

import java.util.Objects;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/19<p/>
 * Time: 17:13<p/>
 */
public class Simple {

    public static void main(String[] args) {

    }

    String[] ss;

    @Test
    public void test1() {
        int i = 10;
//        System.out.println(++i);
        i++;
        System.out.println(i);
    }

    @Test
    public void testArrays() {
        System.out.println(ss);
    }

    @Test
    public void test2(){
        System.out.println(Objects.equals(1,1));
    }

}
