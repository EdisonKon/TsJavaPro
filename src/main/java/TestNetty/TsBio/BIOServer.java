package TestNetty.TsBio;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-24 15:49
 * @from 使用telnet 127.0.0.1 6666 进行客户端连接
 **/
public class BIOServer {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2,
            5,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5),
            new DefaultThreadFactory("线程池"),
            new ThreadPoolExecutor.AbortPolicy() {
    });

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("开始监听");
        while(true){
            final Socket accept = serverSocket.accept();
            System.out.println("有客户端连入");
            threadPoolExecutor.execute(()->{
                handler(accept);
            });

        }
    }

    public static void handler(Socket socket){
        System.out.println("线程: "+Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0,read));
                }else{
                    System.out.println("本次请求完成");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
