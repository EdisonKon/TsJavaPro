package TsThreads;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-01 19:46
 * @from 交替输出 a b c
 **/
public class TurnToPrint {

    public static void main(String[] args) {

        OrderTicket ot = new OrderTicket();
        new Thread(()->{while(true) ot.callA();},"我是A").start();
        new Thread(()->{while(true) ot.callB();},"我是B").start();
        new Thread(()->{while(true) ot.callC();},"我是C").start();


    }

    @Test
    public void TestOddEven() throws InterruptedException {
        OddEven oddEven = new OddEven();
        Thread odd= new Thread(()->{while(oddEven.curent<100) oddEven.printOdd();},"Odd线程");
        Thread even= new Thread(()->{while(oddEven.curent<100) oddEven.printEven();},"Even线程");
        odd.start();
        even.start();
        odd.join();
        even.join();
    }
}


class OddEven{
    public int curent = 1;

    public void printOdd(){
        synchronized (this){
            try {
                while (curent % 2 != 1){
                    this.wait();
                }
                System.out.println(Thread.currentThread().getName() + ":" +curent);
                curent ++;
                this.notify();
            }catch (Exception e){
            }finally {
            }
        }
    }

    public void printEven(){
        synchronized (this){
            try {
                while (curent % 2 != 0){
                    this.wait();
                }
                System.out.println(Thread.currentThread().getName() + ":" +curent);
                curent ++;
                this.notify();
            }catch (Exception e){
            }finally {
            }
        }
    }

}

//循环打印abc
class OrderTicket{
    private int current = 1;
    ReentrantLock lock = new ReentrantLock();
    Condition conditiona = lock.newCondition();
    Condition conditionb = lock.newCondition();
    Condition conditionc = lock.newCondition();


    public void callA(){
        lock.lock();
        try{
            while(current != 1){
                conditiona.await();
            }
            current = 2;
            System.out.println("当前线程:"+Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
            conditionb.signal();
        }catch (Exception e){
        }finally {
            lock.unlock();
        }
    }
    public void callB() {
        lock.lock();
        try{
            while(current != 2){
                conditionb.await();
            }
            current = 3;
            System.out.println("当前线程:"+Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
            conditionc.signal();
        }catch (Exception e){
        }finally {
            lock.unlock();
        }
    }
    public void callC() {
        lock.lock();
        try{
            while(current != 3){
                conditionc.await();
            }
            current = 1;
            System.out.println("当前线程:"+Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
            conditiona.signal();
        }catch (Exception e){
        }finally {
            lock.unlock();
        }
    }
}
