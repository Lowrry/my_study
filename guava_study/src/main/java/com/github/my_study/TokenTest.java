package com.github.my_study;

import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;

/**
 * User: luxiaochun<p/>
 * Date: 2018/2/1<p/>
 * Time: 15:30<p/>
 */
public class TokenTest {

    @Test
    public void test1(){

        Set<Class<? super ArrayList>> classes = TypeToken.of(ArrayList.class).getTypes().rawTypes();
        System.out.println(classes);
    }

}
