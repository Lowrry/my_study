package concurrent.threads;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/7<p/>
 * Time: 17:22<p/>
 */
public class SelfInterruptTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<100000000;i++){
                        i+=1;
                    }
                    System.out.println("i come here ...1");
                    System.out.println("thread status1 = " + Thread.currentThread().isInterrupted());

                    Thread.sleep(1000);

                    System.out.println("i await 1000 ...");
                    System.out.println("thread status3 = " + Thread.currentThread().isInterrupted());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("thread status2 = " + Thread.currentThread().isInterrupted());
                }
                System.out.println("i come here ...2");
            }
        });


        thread.start();
        thread.interrupt();
        System.out.println("already interrupted ... ");
    }

}
