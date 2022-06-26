package TestNetty.TsNio;

import org.junit.Test;

import java.nio.IntBuffer;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-24 17:00
 * @from
 **/
public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i* 5);
        }
        System.out.println("当前位置1:" + intBuffer.position()); // 5
        // buffer都是双向的, 这样是让buffer反转,从头开始推出 如果不加这个, 就会从当前尾部进行推出
        intBuffer.flip();
        System.out.println("当前位置2:" + intBuffer.position()); // 0
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
