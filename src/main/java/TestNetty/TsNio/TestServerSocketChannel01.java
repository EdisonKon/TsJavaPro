package TestNetty.TsNio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-27 10:38
 * @from
 **/
public class TestServerSocketChannel01 {
    public static void main(String[] args) throws IOException {
        //创建一个server
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //创建一个selector
        Selector selector = Selector.open();
        //server绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //server 注册到 selector 并注明关心的事件是OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while(true){
            //等待1s后如果无事件发生,继续
            if(selector.select(1000) == 0){
                System.out.println("服务器等待了1s,无连接");
                continue;
            }
            //如果返回>0 表示已经获取到了事件, 获取事件集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //遍历selectionKeys
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    System.out.println("有新的客户端连接");
                    SocketChannel channel = serverSocketChannel.accept();
                    channel.configureBlocking(false);
                    //将socketChannel注册到selector 并且关注read,且绑定一个buffer
                    channel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {
                    System.out.println("接收到读取事件");
                    //通过key获取channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    channel.read(byteBuffer);
                    System.out.println("从客户端读取到: " + new String(byteBuffer.array()));
                }
                // 移除key 防止重复操作
                iterator.remove();
            }

        }
    }
}
