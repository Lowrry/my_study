package com.github.my_study;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/4<p/>
 * Time: 12:43<p/>
 */
public class ListsTest {

    @Test
    public void test1(){
        ArrayList<String> list = Lists.newArrayList("1", "2", "3", "4", "5");
        List<List<String>> partition = Lists.partition(list, 2);

        System.out.println(partition);
        System.out.println(Lists.reverse(list));

    }

}
