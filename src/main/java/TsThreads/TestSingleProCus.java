package TsThreads;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2019-08-02 21:33
 * @from:
 **/

public class TestSingleProCus {
    static class Guo {
        public int baozi = 0;
    }

    static class Mama implements Runnable {
        Guo guo;
        Object lock;
        public Mama(Guo guo,Object lock) {
            this.guo = guo;
            this.lock = lock;
        }

        public void zuobaozi() throws InterruptedException {
            synchronized (lock){
                while (guo.baozi>=3){
                    lock.wait();
                }
                guo.baozi ++;
                System.out.println("做一个包子,一共"+guo.baozi);
                lock.notifyAll();
            }
        }

        @Override
        public void run(){
            try {
                while(true){
                    zuobaozi();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Erzi implements Runnable{
        Guo guo;
        Object lock;
        String name;
        public Erzi(Guo guo,Object lock,String name) {
            this.guo = guo;
            this.lock = lock;
            this.name = name;
        }
        public void chibaozi()  throws InterruptedException{
            synchronized (lock){
                while (guo.baozi<=0){
                    lock.wait();
                }
                guo.baozi --;
                System.out.println("我是"+ name +"我吃了");
                lock.notifyAll();
            }
        }

        @Override
        public void run() {
            try {
                while(true){
                    chibaozi();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Guo guo = new Guo();
        guo.baozi = 3;
        Object lock = new Object();
        Mama ma = new Mama(guo,lock);
        Erzi er = new Erzi(guo,lock,"老大");
        Erzi er2 = new Erzi(guo,lock,"老二");
        Thread thread1 = new Thread(ma);
        thread1.start();
        Thread thread2 = new Thread(er);
        thread2.start();
        Thread thread3 = new Thread(er2);
        thread3.start();
    }
}
