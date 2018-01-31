package com.github.my_study;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/4<p/>
 * Time: 12:17<p/>
 */
public class RangeSetTest {

    public static void main(String[] args) {
        RangeSet<Integer> r = TreeRangeSet.create();
        r.add(Range.closed(1,10));
//        r.add(Range.closed("12","20"));

        System.out.println(r.contains(8));
    }

}
