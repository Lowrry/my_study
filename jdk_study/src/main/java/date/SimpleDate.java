package date;

import java.util.Random;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/8<p/>
 * Time: 19:17<p/>
 */
public class SimpleDate {

    public static void main(String[] args) {
        Random random1 = new Random(50);
        Random random2 = new Random(50);
        for(int i=0;i<10;i++){
            System.out.println(random1.nextInt());
            System.out.println(random2.nextInt());
        }
    }

}
