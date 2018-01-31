package com.github.my_study.jvm_study;

import org.junit.Test;

/**
 * 基础类型和引用类型的区别.
 *
 * User: luxiaochun<p/>
 * Date: 2018/1/26<p/>
 * Time: 16:39<p/>
 */
public class HelpGCTest {

    @Test
    public void test1() {
        int a = 1;  // 赋值操作
        int b = a;
        a = 2;

        // a和b的值是多少?
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    @Test
    public void test2() {
        User a = new User(1);   // 引用操作
        User b = a;
        a.id = 2;

        System.out.println(a);
        System.out.println(b);
    }

    static class User {
        int id;

        User(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("id=").append(id);
            sb.append('}');
            return sb.toString();
        }
    }


}
