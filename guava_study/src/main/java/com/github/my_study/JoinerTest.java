package com.github.my_study;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/5<p/>
 * Time: 17:08<p/>
 */
public class JoinerTest {

    @Test
    public void test1() {
        List<Integer> strings = Lists.newArrayList(1, null, 3);
        String join = Joiner.on(";").skipNulls().join(strings);
        System.out.println(join);
    }

    @Test
    public void test2() {
        Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split("1, 2 ,,3");
        System.out.println(split);
    }

}
