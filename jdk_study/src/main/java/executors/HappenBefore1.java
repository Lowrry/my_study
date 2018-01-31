package executors;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/21<p/>
 * Time: 15:57<p/>
 */
public class HappenBefore1 {

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

}

class Test{
    static int num = 1;
}

class Thread1 extends Thread{
    @Override
    public void run() {
        Test.num = 2;
    }
}

class Thread2 extends Thread{
    @Override
    public void run() {
        System.out.println("Thread2 ==>" + Test.num);
    }
}
