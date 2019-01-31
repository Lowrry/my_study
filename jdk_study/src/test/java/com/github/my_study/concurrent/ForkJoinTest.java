package com.github.my_study.concurrent;

import org.junit.Test;

import java.util.ArrayList;

/**
 * User: luxiaochun<p/>
 * Date: 2018/10/30<p/>
 * Time: 15:36<p/>
 */
public class ForkJoinTest {

    @Test
    public void forkJoin() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }

        list.forEach(s->System.out.println(10/s));
    }

}
