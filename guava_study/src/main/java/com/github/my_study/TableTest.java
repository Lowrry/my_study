package com.github.my_study;

import com.google.common.collect.HashBasedTable;
import org.junit.Test;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/4<p/>
 * Time: 11:09<p/>
 */
public class TableTest {

    @Test
    public void test1(){
        HashBasedTable<String, String, Integer> table = HashBasedTable.create();
        table.put("student", "a", 1);
        table.put("student", "b", 2);
        table.put("student", "c", 3);
        table.put("teacher", "d", 11);
        table.put("teacher", "e", 22);
        table.put("teacher", "f", 33);

        System.out.println(table.get("student", "a"));
        System.out.println(table.row("student"));
    }

}
