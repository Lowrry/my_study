package com.github.my_study;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.TreeMultimap;
import org.junit.Test;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/4<p/>
 * Time: 10:30<p/>
 */
public class MultiMapTest {

    @Test
    public void test1() {
        ImmutableListMultimap<Object, Object> result = ImmutableListMultimap.builder()
                .put("a", 1)
                .put("a", 2)
                .put("a", 3)
                .put("b", 1)
                .put("b", 2)
                .build();

        System.out.println(result);
    }

    @Test
    public void test2() {
        LinkedListMultimap<Long, String> multimap = LinkedListMultimap.create();
        multimap.put(1L, "a");
        multimap.put(1L, "b");
        multimap.put(1L, "c");
        multimap.put(2L, "d");
//        multimap.entries()

//        System.out.println(multimap);
//
//        for (Map.Entry<Long, String> entry : multimap.entries()) {
//            System.out.println(entry);
//        }

//        System.out.println(multimap.asMap());
//        multimap.asMap().entrySet();

        TreeMultimap<Long, String> map1 = TreeMultimap.create();
        map1.put(2L, "d");
        map1.put(2L, "e");
        map1.put(33L, "g");
        map1.put(33L, "a");
        map1.put(1L, "c");
        map1.put(1L, "a");
        map1.put(1L, "b");


        System.out.println(map1);
    }

}
