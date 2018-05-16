package statics;

/**
 * User: luxiaochun<p/>
 * Date: 2018/3/26<p/>
 * Time: 14:55<p/>
 */
public class StaticTest {

    public static void main(String[] args) {
        System.out.println("start ... ");
//        System.out.println(Lazy.ss);
        System.out.println(Lazy.s);
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new Lazy("hello"));
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("I come to here");
        System.out.println(new Lazy("hello"));
    }

    private static class Lazy {

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Lazy{");
            sb.append("msg='").append(msg).append('\'');
            sb.append('}');
            return sb.toString();
        }

        public String msg;

        public Lazy(String msg) {
            this.msg = msg;
        }

        public static final String s = "s";

        public static final String ss = 1/0 + "";

        static {
            System.out.println("static method started ...");
//            ss = 1 / 0 + "";
        }
    }

}
