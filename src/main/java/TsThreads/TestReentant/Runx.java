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

        Thread.sleep(2000);
        System.out.println("主线程睡2s,通知第1次");
        t.testSignal();
        Thread.sleep(5000);
        System.out.println("主线程睡5s,通知第2次");
        t.testSignal();

    }

    @Test
    public void test() {

    }
}
