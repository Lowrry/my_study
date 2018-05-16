/**
 * User: luxiaochun<p/>
 * Date: 2018/2/11<p/>
 * Time: 15:32<p/>
 */
public class ReflectTest {

    public static class User{
        String name = "test";
        String[] ss = new String[10000];
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++)
        test();
    }

    private static void test() {
        int NUM = 1000000;

        long start1 = System.currentTimeMillis();
        for(int i=0;i<NUM;i++){
            new User();
        }
        System.out.println("use time = " + (System.currentTimeMillis()-start1));

        long start2 = System.currentTimeMillis();
        for(int i=0;i<NUM;i++){
            try {
                User.class.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("use time = " + (System.currentTimeMillis()-start2));

        System.out.println("==========================");
    }

}
