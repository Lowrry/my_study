package com.github.my_study;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/8<p/>
 * Time: 9:55<p/>
 */
public class EventTest {
    // EventBus不是通用型的发布-订阅实现，不适用于进程间通信。
    // 也就是只能在进程内,线程间通信.

    @Test
    public void test1() {
        EventBus eventBus = new EventBus();
        eventBus.register(new EventListener());

        eventBus.post(new Message("Hello"));
    }

    @Test
    public void test2() {
        EventBus eventBus = new EventBus();
        eventBus.register(new EventListener());

        eventBus.post("Empty message");
    }

    @Test
    public void test3(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        AsyncEventBus asyncEventBus = new AsyncEventBus("Async", executorService);
        asyncEventBus.register(new EventListener());

        asyncEventBus.post("Empty async msg");
    }

    private static class EventListener {

        @Subscribe
        public void receive(Message message) {
            System.out.println("Receive : " + message);
        }

        @Subscribe
        public void receive(DeadEvent deadEvent) {
            System.out.println("Receive deadEvent : " + deadEvent);
        }
    }


    private static class Message {
        String msg;

        public Message(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Message{");
            sb.append("msg='").append(msg).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

}
