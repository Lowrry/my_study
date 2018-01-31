package com.github.my_study;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/3<p/>
 * Time: 9:55<p/>
 */
public class SplitterTest {

    @Test
    public void split(){
        String name = "1,2,,, 3 ,,4";
        Iterable<String> split1 = Splitter.on(",").split(name);
        System.out.println(split1);

        Iterable<String> split2 = Splitter.on(",").omitEmptyStrings().split(name);
        System.out.println(split2);

        Iterable<String> split3 = Splitter.on(",").omitEmptyStrings().trimResults().split(name);
        System.out.println(split3);
    }

    @Test
    public void map(){
        String s = "key1:1;key2: 2   ; key3:  3   ";
        Map<String, String> map = Splitter.on(";").trimResults().withKeyValueSeparator(":").split(s);
        System.out.println(map);
    }

    @Test
    public void arrayList(){
        List<String> strings = Lists.newArrayList("aa", "bb", "cc");

        System.out.println(strings);
    }

}
