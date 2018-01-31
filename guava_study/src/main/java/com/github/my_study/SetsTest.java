package com.github.my_study;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;

import static com.google.common.collect.Sets.newHashSet;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/3<p/>
 * Time: 11:08<p/>
 */
public class SetsTest {

    @Test
    public void testSet(){
        HashSet<Integer> setA = newHashSet(1, 2, 3, 4, 5);
        HashSet<Integer> setB = newHashSet(4, 5, 6, 7, 8);

        Sets.SetView<Integer> union = Sets.union(setA, setB);
        System.out.println(union);

        Sets.SetView<Integer> difference = Sets.difference(setA, setB);
        System.out.println(difference);

        Sets.SetView<Integer> intersection = Sets.intersection(setA, setB);
        System.out.println(intersection);
    }

}
