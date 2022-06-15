package TsThreads;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-15 16:43
 * @from
 **/
public class TestOddEven {
    private static Object lock =  new Object();
    private static int number = 1;
    private static boolean flag = false; //是否偶数
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (number < 100){
                synchronized (lock){
                    while (flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("奇数:" + number);
                    number += 1;
                    flag = true;
                    lock.notify();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (number <= 100){
                synchronized (lock){
                    while (!flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("偶数:" + number);
                    number += 1;
                    flag = false;
                    lock.notify();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
