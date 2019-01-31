package com.github.my_study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2018/11/5<p/>
 * Time: 11:23<p/>
 */
public class TempTest {

    @Test
    public void testForceTransfer() {
        long id = 100000000566L;

        System.out.println((int) id);
    }

    @Test
    public void testNumberFormatException() {
        Long id = null;
        System.out.println(Long.valueOf(id));
    }

    @Test
    public void testStringCut() {
        String ss = "lxc_test ";
        System.out.println(ss);
        System.out.println(ss.substring(0, ss.length() - 1));
    }

    @Test
    public void testFormat() {
        Long id = 12345L;
        String result = String.format("%08d", id);
        System.out.println(result);
    }

    @Test
    public void testZero() {
        double a = 0.0;
        System.out.println(a==0);
    }

    @Test
    public void testTongPeiFu() {
        List<?> list = new ArrayList<>();
        list.get(0).toString();
    }

    class GenerateTest<T>{
        public <T> void show(T t){

        }
    }

}
