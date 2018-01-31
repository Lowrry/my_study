package com.github.my_study;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import org.junit.Test;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/4<p/>
 * Time: 9:52<p/>
 */
public class MultiSetTest {

    @Test
    public void test1(){
        ImmutableMultiset<Object> result = ImmutableMultiset.builder().add("a", "b").build();

        System.out.println(result.count("a"));
    }

    @Test
    public void test2(){
        HashMultiset multi = HashMultiset.create();

        multi.add("a");
        multi.add("a");
        multi.add("a");
        multi.add("c");
        multi.add("a");
        multi.add("b");
        multi.add("b");

        System.out.println(multi.count("a"));
        System.out.println(multi.count("b"));
        System.out.println(multi.count("c"));
    }

}
