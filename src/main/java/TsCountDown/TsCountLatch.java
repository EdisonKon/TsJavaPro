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
        //当await等待的线程 经过count-1到小于0的时候, 所有的await的线程都会被唤醒
        for (int i = 0; i < 7; i++) {
            int finalI = i;
            Thread t1 = new Thread(()->{
                try {
                    if(finalI <2){
                        countDownLatch.await();
                        System.out.println(Thread.currentThread().getName() + "执行等会儿");
                    }
                } catch (Exception e) {
                }finally {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "执行-1");
                    countDownLatch.countDown();
                }
            },"当前线程:" + i);
            t1.start();
        }
        countDownLatch.await();
        System.out.println("mmmmmmm");
    }
}
