package com.github.my_study;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/5<p/>
 * Time: 17:56<p/>
 */
public class InvokableTest {

    @Test
    public void test() throws NoSuchMethodException {
        Method getMethod = List.class.getMethod("get", int.class);
        Invokable<List<String>, ?> invokable = new TypeToken<List<String>>() {}.method(getMethod);
        assertEquals(TypeToken.of(String.class), invokable.getReturnType()); // Not Object.class!
        assertEquals(new TypeToken<List<String>>() {}, invokable.getOwnerType());
    }

}
