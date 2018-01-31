package Jdk.jvmBlog;

/**
 * User: luxiaochun<p/>
 * Date: 2017/10/19<p/>
 * Time: 19:21<p/>
 */
public class GarbageTest {

    static class Node{
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

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation1 = allocation2 = allocation3 = null;
        System.out.println(new byte[2 * _1MB]);
//
//
//        allocation1 = new byte[2 * _1MB];
//        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation1 = allocation2 = null;
//        System.out.println(new byte[2 * _1MB]);


//        allocation1 = new byte[2 * _1MB];
//        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation1 = null;
//        System.out.println(new byte[2 * _1MB]);

//        Node a = new Node("a", allocation1);
//        Node b = new Node("b", allocation2);
//        Node c = new Node("c", allocation3);
//        Node d = new Node("d", allocation4);



    }

}
