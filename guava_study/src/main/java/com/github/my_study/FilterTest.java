package com.github.my_study;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/4<p/>
 * Time: 16:25<p/>
 */
public class FilterTest {

    @Test
    public void test1() {
        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");

        ImmutableList<String> list = FluentIterable.from(strings).filter(input -> {
            if (input == null || "2".equals(input)) {
                return false;
            }
            return true;
        }).toList();

        System.out.println(list);
    }

    @Test
    public void test2() {
        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");
        Collection<String> result = Collections2.filter(strings, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                if ("2".equals(input)) return false;
                return true;
            }
        });
        System.out.println(result);
    }

    @Test
    public void test3(){
        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");
        Iterator<Integer> result = Iterators.transform(strings.iterator(), new Function<String, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable String input) {
                return 0;
            }
        });

        while (result.hasNext()){
            System.out.println(result.next());
        }
    }

    @Test
    public void test4(){
        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");

    }

}
