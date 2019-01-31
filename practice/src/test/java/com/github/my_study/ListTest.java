package com.github.my_study;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

/**
 * User: luxiaochun<p/>
 * Date: 2018/10/15<p/>
 * Time: 17:11<p/>
 */
public class ListTest {

    @Test
    public void testRemove(){
        ArrayList<String> list = Lists.newArrayList("a", "b", "c");

        for (String s : list) {
            list.remove(s);
        }

        System.out.println(list);
    }

}
