package TsYzTest;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: yangzhi
 * @Description: com.test.RingBuffer
 * @Date: Create in 2020-03-24 1219
 */
public class RingMain {
    public static volatile List list = Collections.synchronizedList(new LinkedList<>());
    public static void main(String[] args) throws InterruptedException {
//        MyEventHandler myEventHandler = new MyEventHandler();
//        for(int i=0;i<10;i++)
//        {
//            Thread thread = new Thread(new WriterWorker(myEventHandler));
//            thread.start();
//        }
//        Thread readThread = new Thread(new ReadWorker(myEventHandler));
//        readThread.start();



        for(int i=0;i<10;i++)
        {
            Thread thread = new Thread(new CommonWriterWorker());
            thread.start();
        }
        Thread readThread = new Thread(new CommonReadWorker());
        readThread.start();

//        System.out.println("最后数组数量为"+MyEventHandler.arrayList.size());
    }
}
