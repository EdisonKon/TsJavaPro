package TsThreads.TestReentant;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-07-25 15:40
 * @from
 **/
public class Runx {
    public Runx() {

    }
    public static void main(String[] args) throws InterruptedException {
        TestReentantLock t = new TestReentantLock();
        MyThread thread = new MyThread(t);
        thread.start();
        MyThread thread2 = new MyThread(t);
        thread2.start();

        Thread.sleep(100);
        while(!t.lock.tryLock()){
            System.out.println("获取锁失败");
            Thread.sleep(500);
        }
        System.out.println("获取锁成功");
        t.lock.unlock();
        System.out.println("放锁");
        System.out.println("主线程睡2s,通知第1次");
        t.testSignal();
        Thread.sleep(5000);
        System.out.println("主线程睡5s,通知第2次");
        t.testSignal();
    }

    public static void main2(String[] args) throws InterruptedException {
        TestReentantLock t = new TestReentantLock();
        MyThread thread = new MyThread(t);
        thread.start();
        MyThread thread2 = new MyThread(t);
        thread2.start();

    }

    @Test
    public void test() {

    }
}
