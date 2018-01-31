package concurrent;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/15<p/>
 * Time: 11:43<p/>
 */
public class Test4 {

    public Test4() {
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        for (; ; ) {
//            Thread.sleep(100);

            ThreadLocal<User> t = new ThreadLocal<User>(){
                @Override
                protected User initialValue() {
                    return new User();
                }
            };
            System.out.println(t.get().name);
        }
    }

}

class User {
    String name = "test";

    Long id = 2L;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
