package TsThreads.TestReentant;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-21 16:21
 * @from 让线程A B C 顺序执行
 **/
public class TsOrderExec {


    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{while(true){ticket.printNumfor1();}},"A").start();
        new Thread(()->{while(true){ticket.printNumfor2();}},"B").start();
        new Thread(()->{while(true){ticket.printNumfor3();}},"C").start();
    }


    static class Ticket{
        private int number = 0;
        Lock lockx = new ReentrantLock();
        final Condition condition1 = lockx.newCondition();
        final Condition condition2 = lockx.newCondition();
        final Condition condition3 = lockx.newCondition();

        public void printNumfor1(){
            lockx.lock();
            try{
                while(number % 3 == 1){
                    condition1.await();
                }
                System.out.println(Thread.currentThread().getName()+"执行:number -> " + number);
                Thread.sleep(1000);
                number ++ ;
                condition2.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lockx.unlock();
            }
        }
        public void printNumfor2(){
            lockx.lock();
            try{
                while(number % 3 == 2){
                    condition2.await();
                }
                System.out.println(Thread.currentThread().getName()+"执行:number -> " + number);
                Thread.sleep(1000);
                number ++ ;
                condition3.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lockx.unlock();
            }
        }
        public void printNumfor3(){
            lockx.lock();
            try{
                while(number % 3 == 0){
                    condition3.await();
                }
                System.out.println(Thread.currentThread().getName()+"执行:number -> " + number);
                Thread.sleep(1000);
                number ++ ;
                condition1.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lockx.unlock();
            }
        }
    }
}
