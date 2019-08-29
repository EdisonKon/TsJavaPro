import org.junit.Test;

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
        System.out.println(1);
    }
    @Test
    public void test() {

    }
}
