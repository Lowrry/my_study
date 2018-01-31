package innerclass;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/20<p/>
 * Time: 15:44<p/>
 */
public class Outer {

    String name = "outer";

    public void printing(){
        new Inner().print();
    }

    class Inner{
        String name = "inner";

        void print(){
            System.out.println(Outer.this.name);
            System.out.println(this.name);
        }
    }

    public static void main(String[] args) {
        new Outer().printing();
    }

}
