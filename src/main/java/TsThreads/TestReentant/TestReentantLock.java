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
    private ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public TestReentantLock() {
    }

    public void testAwait(){
        lock.lock();
        System.out.println("开始wait:"+Thread.currentThread().getName());
        try {
            condition.await();
            Thread.sleep(3000);
            System.out.println("wait 3s :"+Thread.currentThread().getName()+"操作完成");

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
