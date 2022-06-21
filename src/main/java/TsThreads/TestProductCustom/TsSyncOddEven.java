package TsThreads.TestProductCustom;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-21 15:30
 * @from  打印奇偶数
 **/
public class TsSyncOddEven {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            while (ticket.number < 100) {
                try {
                    ticket.odd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            while (ticket.number < 100) {
                try {
                    ticket.even();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }


    static class Ticket{
        private int number = 1;

        public synchronized void odd() throws InterruptedException {
            while(number % 2 == 0){
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + "进行了打印奇数操作,当前:" + number);
            number += 1;
            this.notifyAll();
        }

        public synchronized void even() throws InterruptedException {
            while(number % 2 == 1){
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + "进行了打印偶数操作,当前:" + number);
            number += 1;
            this.notifyAll();
        }
    }

}
