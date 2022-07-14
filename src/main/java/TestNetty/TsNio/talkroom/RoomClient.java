package TestNetty.TsNio.talkroom;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//import static sun.jvm.hotspot.runtime.PerfMemory.start;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-27 13:44
 * @from
 **/
public class RoomClient {
    private final String HOST = "127.0.0.1";
    private final int PORT = 666;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public RoomClient() throws Exception{
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(HOST,PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        username = socketChannel.getLocalAddress().toString().substring(1);
    }

    public void sendInfo(String info){
        info = username + " 说: " + info;
        try{
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readInfo(){
        try{
            int select = selector.select();
            if(select > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if (next.isReadable()) {
                        SocketChannel sc = (SocketChannel) next.channel();
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        sc.read(allocate);
                        String s = new String(allocate.array());
                        System.out.println(s);
                    }
                    //防止重复
                    iterator.remove();
                }
            }else{
                System.out.println("没有人说话");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        RoomClient roomClient = new RoomClient();
        new Thread(()->{
            while(true){
                roomClient.readInfo();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String s = scanner.nextLine();
            roomClient.sendInfo(s);
        }
    }

}
