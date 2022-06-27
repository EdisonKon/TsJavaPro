package TestNetty.TsNio.talkroom;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-27 11:34
 * @from
 **/
public class RoomServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 666;

    public RoomServer() {
        try {
            this.selector = Selector.open();
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.bind(new InetSocketAddress(PORT));
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen(){
        try {
            while(true){
                int count = selector.select(100);
                if(count > 0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if(key.isAcceptable()){
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + ": 上线了");
                        }
                        if(key.isReadable()){
                            //处理读的方法
                            readData(key);
                        }
                        //防止重复
                        iterator.remove();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 读取信息
     * @param key 消息来源的channel对应的key
     */
    public void readData(SelectionKey key){
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(byteBuffer);
            if(count >0){
                String msg = new String(byteBuffer.array());
                System.out.println("从客户端接收到:" + msg +" , 进行转发! ");
                transMsgToOtherClients(msg,socketChannel);
            }
        }catch (Exception e){
            try {
                System.out.println(socketChannel.getRemoteAddress() + "下线了...");
                //下线或者异常后 需要取消注册
                key.cancel();
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 转发方法
     * @param msg 消息
     * @param sc 消息来源客户端的channel
     */
    public void transMsgToOtherClients(String msg, SocketChannel sc){
        // 注意这里的keys是所有的连入的channel selectionKeys是有事件产生的channel
        for (SelectionKey selectedKey : selector.keys()) {
            SelectableChannel channel = selectedKey.channel();
            if(channel instanceof SocketChannel && channel != sc){
                SocketChannel otherSc = (SocketChannel) channel;
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                try {
                    otherSc.write(wrap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        RoomServer roomServer = new RoomServer();
        roomServer.listen();
    }
}
