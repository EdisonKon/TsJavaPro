package zOther;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-07-20 09:48
 * @from
 **/
public class TsSocket {
    public void socketserver() throws IOException {
        ServerSocket sc = new ServerSocket(9090);
        Socket socket = sc.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes, 0, len,"UTF-8"));
        }
        System.out.println("get message from client: " + sb);
        inputStream.close();

        socket.close();
        sc.close();
    }

    public void client() throws IOException {
        Socket socket = new Socket("localhost",9090);

        OutputStream outputStream = socket.getOutputStream();
        String message="你好";
        socket.getOutputStream().write(message.getBytes("UTF-8"));
        outputStream.close();
        socket.close();
    }
}
