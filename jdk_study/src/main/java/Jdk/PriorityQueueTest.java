package Jdk;

import java.util.PriorityQueue;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/6<p/>
 * Time: 9:37<p/>
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        //Collection 集合类的层次结构
        PriorityQueue<String> queue = new PriorityQueue<String>();
        queue.offer("22");
        queue.offer("11");
        queue.offer("33");
        queue.offer("44");
        queue.offer("53");
        queue.offer("63");
        queue.offer("73");
        queue.offer("83");
        queue.offer("93");
        queue.offer("31");
        queue.offer("32");
        queue.offer("33");
        queue.offer("34");
        queue.offer("35");
        queue.offer("36");

        System.out.println(queue);

    }

}
