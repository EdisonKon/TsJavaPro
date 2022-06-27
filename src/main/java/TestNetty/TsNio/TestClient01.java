package TestNetty.TsNio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-27 11:01
 * @from
 **/
public class TestClient01 {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",6666);
        if(!socketChannel.connect(inetSocketAddress)){
            while(!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间,客户端不会阻塞,所以可以做其他工作...");
            }
        }
        String s = "你好啊";
        // 不需要指定大小, 直接使用warp
        ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes());
        //发送数据,将buffer写入channel
        socketChannel.write(byteBuffer);
        System.in.read();
    }
}
