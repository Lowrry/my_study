package Jdk.jvmBlog;

import java.io.IOException;

public class SystemGc {

    public static class Node {

        String name;    //节点名称
        Node next;      //指向下一个节点

        Node(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {    //JVM GC时会调用finalize方法
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

    //test
    public static void main(String[] args) throws IOException {
        SystemGc systemGc = new SystemGc();
        systemGc.removeHead();

        System.gc();

        System.in.read();
    }

}
