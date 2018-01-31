package com.github.my_study;

import com.google.common.base.Function;
import com.google.common.collect.*;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/4<p/>
 * Time: 13:37<p/>
 */
public class MapsTest {

    @Test
    public void test1() {
        List<String> list = Lists.newArrayList("a", "bb", "ccc");

        ImmutableMap<Integer, String> map = Maps.uniqueIndex(list, new Function<String, Integer>() {
            @Override
            public Integer apply(@Nullable String input) {
                return input.length();
            }
        });
        System.out.println(map);
    }

    @Test
    public void test2() {
        List<String> list = Lists.newArrayList("a", "bb", "ccc", "dd");

        ImmutableListMultimap<Integer, String> map = Multimaps.index(list, new Function<String, Integer>() {
            @Override
            public Integer apply(@Nullable String input) {
                return input.length();
            }
        });
        System.out.println(map);
        System.out.println(map.get(2));
    }

}
