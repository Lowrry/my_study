package com.github.my_study.jvm_study;

import java.io.IOException;

/**
 * Jdk中的置null 和 help gc
 */
public class SystemGc {

    public static class Node {

        String name;    //节点名称
        Node next;      //指向下一个节点

        Node(String name) {
            this.name = name;
        }

        /*
         * 即使在可达性分析算法中不可达的对象,也不是马上就死的.要宣告一个对象死亡.
         * 至少要经历两次标记过程:如果对象在可达性分析后发现没有与GC ROOTS相连的引用链,那么他将会被第一次标记.
         * 并进行一次筛选.筛选的条件是此对象有没有必要执行finalize方法,如果对象没有覆盖finalize方法或者finalize方法已经被执行过了.
         * 虚拟机将视为”没有必要执行”,如果有必要执行finalize方法,那么这个对象会被放入F-Queue队列中,
         * 并在稍后由虚拟机创建一个低优先级的Finalizer线程去执行他,这里的执行时值虚拟机会触发这个方法,但不承诺会等待他运行结束.
         * 稍后会进行第二次小规模标记.被标记两次的对象基本上他就是真的被回收了.
         */
        @Override
        protected void finalize() throws Throwable {
            System.out.println("I am removing by GC : " + this.name);
            super.finalize();
        }
    }

    private Node head;

    public void removeHead() {
        Node a = new Node("node_a");    //新建两个node节点
        Node b = new Node("node_b");

        this.head = a;       //head指向a
        a.next = b;     //a next执行b,形成一个链表

        unlinkHead();   //其实链表头结点的删除动作,就是将head指向下一个节点,然后头结点不可到达,就会被GC
    }

    public void unlinkHead() {
        Node first = this.head;
        this.head = first.next;      //head指向下一个节点.
//        first.next = null;  //help gc
    }

    // -server -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
    public static void main(String[] args) throws IOException {
        SystemGc systemGc = new SystemGc();
        systemGc.removeHead();

        System.gc();

        System.in.read();
    }

}
