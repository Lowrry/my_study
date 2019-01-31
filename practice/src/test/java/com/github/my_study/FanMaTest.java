package com.github.my_study;

import org.junit.Test;

/**
 * User: luxiaochun<p/>
 * Date: 2018/5/18<p/>
 * Time: 17:36<p/>
 */
public class FanMaTest {

    @Test
    public void test1(){
        System.out.println( 7 & -7);

        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(-7));

        // 7 & -7
        // 源码 000...00111
        // 反码 111...11000
        // 补码 111...11001

        // 8 & -8
        // 源码 000...01000
        // 反码 111...10111
        // 补码 111...11000
    }
}
