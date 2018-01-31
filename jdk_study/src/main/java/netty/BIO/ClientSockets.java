package netty.BIO;

import java.io.*;
import java.net.Socket;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/23<p/>
 * Time: 10:02<p/>
 */
public class ClientSockets {

    public static void main(String[] args) {
        int port = 8080;
        String address = "127.0.0.1";

        Socket socket;
        PrintWriter printWriter = null;
        try {
            socket = new Socket(address, port);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printWriter = new PrintWriter(socket.getOutputStream(),false);  //autoFlush为true只是在printlin时才会自动flush
            //如果是readLine必须用println或者字符串后面跟一个换行符,否则server读取会一直阻塞的.
            printWriter.write("CURRENT_DATE");
//            printWriter.flush();
            printWriter.close();



//            printWriter.new

//            BufferedWriter bufferedWriter = new BufferedWriter(new Buffer)

            String resp = bufferedReader.readLine();
            System.out.println("Resp => " + resp);

//            //接收服务端返回
//            StringBuilder s = new StringBuilder();
//            char[] read = new char[1000];
//            while (inputReader.read(read) != -1) {
//                s.append(read);
//            }
//            System.out.println("Get from Server => " + s);

            System.out.println("finish send");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

}
