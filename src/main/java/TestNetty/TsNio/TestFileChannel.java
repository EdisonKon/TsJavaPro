package TestNetty.TsNio;


import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-26 17:03
 * @from 写入字符串到指定文件
 **/
public class TestFileChannel {

    /**
     * 写文件
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String s = " 你好 NIO0000000000";
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/edison/Desktop/nihao.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024); // 1024 足够长度
        byteBuffer.put(s.getBytes());
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();



    }

    /**
     * 读取文件
     * @throws IOException
     */
    @Test
    public void getFile() throws IOException {
        File file = new File("/Users/edison/Desktop/nihao.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer1 = ByteBuffer.allocate((int) file.length());
        channel.read(byteBuffer1);
        byteBuffer1.flip();
        System.out.println(new String(byteBuffer1.array()));
        fileInputStream.close();
    }


    /**
     * 通过一个buffer对文件进行copy 既读又写
     * @throws IOException
     */
    @Test
    public void copyFile() throws IOException {
        File file = new File("/Users/edison/Desktop/nihao.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/edison/Desktop/nihao2.txt");
        FileChannel channel1 = fileOutputStream.getChannel();

        while(true){
            // 关键, 重置buffer的属性值 limit position等
            byteBuffer.clear();
            int read = channel.read(byteBuffer);
            if(read == -1){
                break;
            }
            byteBuffer.flip();
            channel1.write(byteBuffer);
        }
        fileOutputStream.close();
        fileInputStream.close();
    }

    /**
     * 直接使用channel的transferTo方法不手动申请buffer进行拷贝
     * @throws IOException
     */
    @Test
    public void copyFileByTransfer() throws IOException {
        File file = new File("/Users/edison/Desktop/nihao.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/edison/Desktop/nihao3.txt");
        FileChannel source = fileInputStream.getChannel();
        FileChannel target = fileOutputStream.getChannel();
        source.transferTo(0,file.length(),target);
        source.close();
        target.close();
        fileOutputStream.close();
        fileInputStream.close();
    }

}
