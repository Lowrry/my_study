package com.github.my_study;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import org.junit.Test;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/5<p/>
 * Time: 17:19<p/>
 */
public class CharMatcherTest {

    @Test
    public void test1(){
        String ss = "dafkdf123dkfjk34434";
        System.out.println(CharMatcher.digit().retainFrom(ss));
    }

    @Test
    public void test2(){
        String ss = "bc";

        CharMatcher range = CharMatcher.inRange('a', 'z');
        System.out.println(range.matches('b'));
    }

    @Test
    public void test3(){
        byte[] bytes = "abc".getBytes(Charsets.UTF_8);
        for (byte b : bytes) {
            System.out.println(b);
        }
    }

}
