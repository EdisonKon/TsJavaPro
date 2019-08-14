package TsThreads.LoopLock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dekai.kong
 * @difficult 可重入自旋锁
 * @create 2019-08-14 19:04
 * @from
 **/
public class TestCanRetantLoopLock {
    public TestCanRetantLoopLock() {

    }

    private AtomicReference<Thread> cas = new AtomicReference<Thread>();
    private int count;
    public void lock() {
        Thread current = Thread.currentThread();
        if (current == cas.get()) { // 如果当前线程已经获取到了锁，线程数增加一，然后返回
            count++;
            return;
        }
        // 如果没获取到锁，则通过CAS自旋
        while (!cas.compareAndSet(null, current)) {
            // DO nothing
        }
    }
    public void unlock() {
        Thread cur = Thread.currentThread();
        if (cur == cas.get()) {
            if (count > 0) {// 如果大于0，表示当前线程多次获取了该锁，释放锁通过count减一来模拟
                count--;
            } else {// 如果count==0，可以将锁释放，这样就能保证获取锁的次数与释放锁的次数是一致的了。
                cas.compareAndSet(cur, null);
            }
        }
    }


    @Test
    public void test() {

    }
}
