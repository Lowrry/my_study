package com.github.my_study.jvm_study;

import org.junit.Test;

/**
 * 探讨GC何时触发的问题
 * <p>
 * User: luxiaochun<p/>
 * Date: 2017/10/19<p/>
 * Time: 19:21<p/>
 */
public class GarbageTest {

    static class Node {
        String name;
        byte[] bytes;
        Node next;

        public Node(String name, byte[] bytes) {
            this.name = name;
            this.bytes = bytes;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("i am gc ... " + this.name);
            super.finalize();
        }
    }

    private static final int _1MB = 1024 * 1024;

    // -server -Xms4m -Xmx4m

    /**
     * -XX:+PrintGC				: 输出GC简要的信息
     * -XX:+PrintGCDetails		: 输出GC详细信息
     * -XX:+PrintGCtimeStamps		: 输出GC时间戳
     * -XX:+PrintGCApplicationStoppedTime	: 输出GC暂停时间
     * -Xlogg 				    : 输出到文件
     */
    @Test
    public void test1() {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = allocation2 = allocation3 = null;
        System.out.println(new byte[2 * _1MB]);


//        allocation1 = new byte[2 * _1MB];
//        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation1 = allocation2 = null;
//        System.out.println(new byte[2 * _1MB]);
//
//
//        allocation1 = new byte[2 * _1MB];
//        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation1 = null;
//        System.out.println(new byte[2 * _1MB]);
//
//        Node a = new Node("a", allocation1);
//        Node b = new Node("b", allocation2);
//        Node c = new Node("c", allocation3);
//        Node d = new Node("d", allocation4);

    }

    //-server -Xms1024m -Xmx1024m -XX:+PrintGCDetails
    @Test
    public void testStringIntern() {
        int i = 0;
        for (; ; ) {
            long start = System.nanoTime();
            String s = (++i + "dfadajr3iur3iu93r3j3jr393r9").intern();
            System.out.println(s + " use time = " + (System.nanoTime() - start));
        }
    }

}
