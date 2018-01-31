package date;

import java.util.Calendar;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/19<p/>
 * Time: 17:13<p/>
 */
public class Simple {

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        instance.add(Calendar.DATE, 30);
        System.out.println(instance.getTime());
    }

}
