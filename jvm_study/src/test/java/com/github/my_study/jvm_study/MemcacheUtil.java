package com.github.my_study.jvm_study;

/**
 * 1. 依赖JVM的ClassLoader类加载机制保证了不会出现同步问题。
 * 2. static变量是在类加载时才加载->连接->初始化的
 *
 * User: luxiaochun<p/>
 * Date: 2018/1/26<p/>
 * Time: 17:04<p/>
 */
public class MemcacheUtil {

    private MemcacheUtil(){
        System.out.println("Init MemcacheUtil ...");        // 这个构造函数何时执行? (1)
    }

    private static class Holder{
        private static MemcacheUtil instance = new MemcacheUtil();  // 这个何时实例化 (2)
    }

    public static MemcacheUtil instance;

    public static MemcacheUtil getInstance(){
        return Holder.instance;
    }


    //test
    public static void main(String[] args) {
        System.out.println("I am started ...");

        System.out.println("Start to load ...");
        MemcacheUtil.getInstance();
        System.out.println("Finish load ...");
    }

}
