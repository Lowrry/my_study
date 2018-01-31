package classes;

import java.lang.reflect.Method;

/**
 * User: luxiaochun<p/>
 * Date: 2017/12/4<p/>
 * Time: 14:54<p/>
 */
public class Class {

    public static void main(String[] args) {
//        User person = new Person();
//
//        System.out.println(User.class.isInterface());
//        System.out.println(person.getClass().getName());

        Method[] methods = Person.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println(method.getDeclaringClass());
            System.out.println(method.getDeclaringClass().getSimpleName());
        }
    }

    interface User{
        String getName();
    }

    static class Person implements User{

        @Override
        public String getName() {
            return "who";
        }
    }

}
