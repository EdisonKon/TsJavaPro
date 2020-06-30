package TsVolatile;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-06-30 16:52
 * @from
 **/
public class TestVolatile {
    /**
     *     增加了volatile后依然会出现小于200000的情况,
     *     因为强制刷新内存并不能解决原子性问题
     *     count++
     *     是非原子操作 转成 atomicinteger后即可解决原子性问题
     */
    static volatile int count = 0;
//    static volatile AtomicInteger count = new AtomicInteger(0);
    public static void addCount(){
        count ++;
//        count.incrementAndGet();
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    addCount();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    addCount();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
