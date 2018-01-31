package com.github.my_study;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/12<p/>
 * Time: 11:33<p/>
 */
public class ShutDownTest {

    public static void main(String[] args) {
        try{
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("I am shut down .. ");
                }
            }));
            for(;;){
                System.out.println("i am waiting ..");
                Thread.sleep(1000);
            }
        }catch (Exception e) {

        }finally {
            System.out.println("I am end ...");
        }
    }

}
