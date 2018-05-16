package file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * User: luxiaochun<p/>
 * Date: 2018/2/24<p/>
 * Time: 15:44<p/>
 */
public class RandomAccessFileTest {

    @Test
    public void test() throws IOException {
        File file = new File("Test.txt");
        RandomAccessFile rdf = new RandomAccessFile(file, "rw");

//        User user = new User();
//        user.setId(111L);
//        user.setName("lxc");
//        user.setAge(29);

        rdf.writeLong(321L);
        rdf.writeBytes("lxc");
        rdf.writeInt(new Integer(29));

        rdf.seek(0);

//        rdf.close();
//
//        rdf = new RandomAccessFile(file,"rw");

        System.out.println(rdf.readLong());

        System.out.println((char)rdf.readByte());
        System.out.println((char)rdf.readByte());
        System.out.println((char)rdf.readByte());

        int age = rdf.readInt();
        System.out.println(age);

        rdf.close();

    }

    private static class User {
        Long id;
        String name;
        int age;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
