package com.github.my_study;

import com.google.common.base.Objects;
import org.junit.Test;



/**
 * User: luxiaochun<p/>
 * Date: 2018/1/3<p/>
 * Time: 19:51<p/>
 */
public class ObjectsTest {

    @Test
    public void test1(){
        System.out.println(Objects.equal("a","b"));
        System.out.println(Objects.equal(1L, 1L));
    }

    @Test
    public void test2(){
        System.out.println(Objects.hashCode("1","2","4"));
        System.out.println(Objects.hashCode("1","2","4"));
        System.out.println(Objects.hashCode("1","4","2"));

    }

}
