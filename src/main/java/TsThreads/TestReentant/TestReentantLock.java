package TsThreads.TestReentant;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-07-25 15:16
 * @from
 **/
public class TestReentantLock {
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public TestReentantLock() {
    }

    public void testAwait(){
        lock.lock();
        try {
            System.out.println("开始wait:"+Thread.currentThread().getName());
            Thread.sleep(3000);
            System.out.println("wait 3s :"+Thread.currentThread().getName()+"操作完成");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void testAwait2(){
        lock.lock();
        System.out.println("开始wait:"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            condition.await();
            Thread.sleep(1000);
            System.out.println("wait 1s :"+Thread.currentThread().getName()+"操作完成");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void testSignal(){
        lock.lock();
        System.out.println("开始Signal:"+Thread.currentThread().getName());
        try{
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }


    @Test
    public void test() {
    }
}
