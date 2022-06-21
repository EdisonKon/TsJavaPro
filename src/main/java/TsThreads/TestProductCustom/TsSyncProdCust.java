package TsThreads.TestProductCustom;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-21 15:30
 * @from 生产消费
 **/
public class TsSyncProdCust {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }


    static class Ticket{
        private int number = 0;

        public synchronized void sub() throws InterruptedException {
            while(number==0){
                this.wait();
            }
            number -= 1;
            System.out.println(Thread.currentThread().getName() + "进行了卖票操作,票剩余:" + number);
            this.notifyAll();
        }

        public synchronized void add() throws InterruptedException {
            while(number>0){
                this.wait();
            }
            number += 1;
            System.out.println(Thread.currentThread().getName() + "进行了补票操作,票剩余:" + number);
            this.notifyAll();
        }
    }

}
