import org.junit.Test;

import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-29 11:28
 * @from
 **/
public class TestCyclicBarrier {
    public TestCyclicBarrier() {

    }
    // 订单队列
    Vector<String> pos = new Vector<>();
    // 派送单队列
    Vector<String> dos = new Vector<>();
    Executor executor = Executors.newFixedThreadPool(1);
    ExecutorService executors = new ThreadPoolExecutor(1, 1, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10) {
    });
    final CyclicBarrier barrier =new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    TestCyclicBarrier.this.check();
                }
            });
        }
    });

    void check(){
        String p = pos.remove(0);
        String d = dos.remove(0);
        System.out.println("对账:"+p+"---"+d);
    }

    void checkAll(){
        // 循环查询订单库
        Thread T1 = new Thread(()->{
            while(true){
                Double d = Math.random()*100;
                try {
                    Thread.sleep(d.intValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 查询订单库
                pos.add("S1"+d.intValue());
                System.out.println("我T1塞值了,睡了"+d.intValue());
                // 等待
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T1.start();
        // 循环查询运单库
        Thread T2 = new Thread(()->{
            while(true){
                Double d = Math.random()*100;
                try {
                    Thread.sleep(d.intValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 查询运单库
                dos.add("S2"+d.intValue());
                System.out.println("我T2塞值了,睡了"+d.intValue());
                // 等待
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T2.start();
    }



    @Test
    public void test() throws InterruptedException {
        checkAll();

        Thread.sleep(3000);
    }
}
