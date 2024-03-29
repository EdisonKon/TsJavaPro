package TestJVM;


import java.util.HashMap;
import java.util.Map;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-06-05 10:11
 * @from
 * 尝试写一个内存泄漏的代码
 **/
public class TestMemoryLeak {
    static Map<String,String> value = new HashMap<>(16);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    value.put("[6661]"+i,new String(new byte[1024*1024]));
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    value.put("[6662]"+i,"[6662][6662][6662][6662][6662][6662][6662][6662][6662][6662]");
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        Thread.sleep(1000000);
        System.out.println("我完事了");
    }
}
