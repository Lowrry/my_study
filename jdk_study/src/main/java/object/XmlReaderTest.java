package object;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/6<p/>
 * Time: 18:29<p/>
 */
public class XmlReaderTest{

    XmlReader xmlReader;

    public XmlReaderTest(XmlReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    public static void main(String[] args) {
        XmlReader xmlReader = new XmlReader();
        System.out.println(xmlReader);

        XmlReaderTest xmlReaderTest = new XmlReaderTest(xmlReader);

        xmlReaderTest.xmlReader.name = "hello";
        System.out.println(xmlReaderTest);
        System.out.println(xmlReader);
    }

}

class XmlReader{

    public XmlReader() {
    }

    String name = "test";

    public XmlReader(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("XmlReader{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
