package com.github.my_study;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/22<p/>
 * Time: 19:29<p/>
 */
public class Temp {

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("i am shut down");
                            }
                        }
                )
        );

        Double d = 1.931;
        System.out.println(d.longValue());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
