package com.github.my_study;

import com.google.common.collect.ImmutableListMultimap;
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

}
