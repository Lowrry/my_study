package netty.BIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/23<p/>
 * Time: 9:49<p/>
 */
public class ServerSockets {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = null;

        while (true) {
            try {
                doReceive(port, serverSocket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void doReceive(int port, ServerSocket serverSocket) throws IOException {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("ServerSockets is Started at port : " + port);

            Socket accept = null;
            while (true) {
                accept = serverSocket.accept();
                System.out.println("accept success ...");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream())); //Reader是用于字节和字符转化的.
                accept.getInputStream();//Reader是用于字节和字符转化的.

                PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);

                String result = "";
                char[] ss = new char[1000];
                int index = 0;
                while (true) {
                    int read = bufferedReader.read();
                    System.out.println("read result : " + (char)read + "," + read);
                    if (read == -1) {
                        System.out.println("break ...");
                        break;
                    }

                    ss[index] = (char)read;
                    index++;
                }

                System.out.println("Result => " + new String(ss).trim());

//                String send = result + " : " + atomicInteger.getAndIncrement() + "\r\n";
//                printWriter.write(send);
//                printWriter.flush();
////                printWriter.println();
//
//                printWriter.close();
//                System.out.println("Send success => " + send);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

}
