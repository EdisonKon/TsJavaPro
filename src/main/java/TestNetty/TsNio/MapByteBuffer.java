package TestNetty.TsNio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-26 17:57
 * @from
 * 可以直接映射打内存中进行修改, 不需要拷贝一份
 **/
public class MapByteBuffer {


    @Test
    public void test() throws IOException {
        // 创建一个随机访问文件, 指定访问类型为读写
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/edison/Desktop/nihao.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        // 映射到内存中并且起始位置是0, 最大的长度是5 ( 注意 5不是index而是长度)
        // 新建一个可以在内存中直接修改文件的buffer
        MappedByteBuffer mapBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mapBuffer.put(0,(byte)'H');
//        mapBuffer.put(3, (byte) '哈');
        channel.close();
        randomAccessFile.close();
    }
}
