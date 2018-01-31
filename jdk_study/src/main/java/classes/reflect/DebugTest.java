package classes.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/25<p/>
 * Time: 17:19<p/>
 */
public class DebugTest {

    public void print(){
        System.out.println("start ...");
        System.out.println("end ...");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        DebugTest debugTest = new DebugTest();
        Method print = debugTest.getClass().getMethod("print", null);
        print.invoke(debugTest, null);
        System.out.println("### success ###");
    }

}
