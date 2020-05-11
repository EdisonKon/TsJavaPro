package TestSync;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2020-05-11 21:58
 * @from:
 **/

public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        TestSync ts = new TestSync();
        Thread thread1 = new Thread(new MyRunable(ts));
        thread1.start();
        Thread thread2 = new Thread(new MyRunable(ts));
        thread2.start();
        thread1.join();
        thread2.join();
    }

    static class MyRunable implements Runnable{
        private TestSync ts;

        public MyRunable(TestSync ts) {
            this.ts = ts;
        }

        @Override
        public void run() {
            //唯一对象，对ts进行加锁，结果没问题
            System.out.println(ts);
            for (int i = 0; i < 100; i++) {
                ts.setIndex();
            }
            System.out.println("ts = " + ts.getIndex());
        }
    }

    static class MyRunable2 implements Runnable{
        //唯一对象，对TestSync.class进行加锁，结果没问题
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                TestSync.setIndexStatic();
            }
            System.out.println("ts = " + TestSync.index);
        }
    }

}
