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
        Thread thread2 = new Thread(new MyRunable(ts));
        thread1.start();
        thread2.start();

        Thread.sleep(2000);

        Thread thread21 = new Thread(new MyRunable2());
        Thread thread22 = new Thread(new MyRunable2());
        thread21.start();
        thread22.start();

        Thread.sleep(2000);

        Thread thread31 = new Thread(new MyRunable3());
        Thread thread32 = new Thread(new MyRunable3());
        thread31.start();
        thread32.start();
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
            for (int i = 0; i < 1000; i++) {
                ts.setIndex();
            }
            System.out.println("ts1 = " + ts.getIndex());
        }
    }

    static class MyRunable2 implements Runnable{
        //唯一对象，对TestSync.class进行加锁，结果没问题
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                TestSync.setIndexStatic();
            }
            System.out.println("ts2 = " + TestSync.index);
        }
    }

    static class MyRunable3 implements Runnable{

        @Override
        public void run() {
            //非唯一对象，对ts进行加锁，如果index是static的话，有问题
            TestSync ts = new TestSync();
            for (int i = 0; i < 1000; i++) {
                ts.setIndexToStatic();
            }
            System.out.println("ts3 = " + TestSync.index);
        }
    }

}
