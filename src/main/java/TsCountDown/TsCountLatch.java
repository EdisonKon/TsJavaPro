package TsCountDown;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-07-07 13:39
 * @from
 **/
public class TsCountLatch {
    static CountDownLatch countDownLatch = new CountDownLatch(3);
    public static void main(String[] args) throws InterruptedException {
        //当count-到0的时候,主线程就会执行,不管后续再继续－也不会再等,到0时候会相当于notify所有await的线程
        for (int i = 0; i < 7; i++) {
            Thread t1 = new Thread(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("zzzz");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
            t1.start();
        }
        countDownLatch.await();
        System.out.println("mmmmmmm");
    }
}
