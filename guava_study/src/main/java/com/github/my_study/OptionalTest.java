package com.github.my_study;

import com.google.common.base.Optional;
import org.junit.Test;


/**
 * User: luxiaochun<p/>
 * Date: 2018/1/3<p/>
 * Time: 19:02<p/>
 */
public class OptionalTest {

    @Test
    public void test1(){
        Optional o = Optional.fromNullable(1);
        System.out.println(o.isPresent());
        System.out.println(o.get());
    }

}
