package TsThreads.LoopLock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dekai.kong
 * @difficult 自旋锁
 * @create 2019-08-14 18:53
 * @from
 * lock（)方法利用的CAS，当第一个线程A获取锁的时候，能够成功获取到，不会进入while循环，
 * 如果此时线程A没有释放锁，另一个线程B又来获取锁，此时由于不满足CAS，所以就会进入while循环，
 * 不断判断是否满足CAS，直到A线程调用unlock方法释放了该锁。
 *
 *
 * 自旋锁存在的问题
 * 1.如果某个线程持有锁的时间过长，就会导致其它等待获取锁的线程进入循环等待，消耗CPU。使用不当会造成CPU使用率极高。
 * 2.上面Java实现的自旋锁不是公平的，即无法满足等待时间最长的线程优先获取锁。不公平的锁就会存在“线程饥饿”问题。
 *
 * 自旋锁的优点
 *
 * 1.自旋锁不会使线程状态发生切换，一直处于用户态，即线程一直都是active的；不会使线程进入阻塞状态，
 * 减少了不必要的上下文切换，执行速度快
 *
 * 2.非自旋锁在获取不到锁的时候会进入阻塞状态，从而进入内核态，当获取到锁的时候需要从内核态恢复，
 * 需要线程上下文切换。 （线程被阻塞后便进入内核（Linux）调度状态，这个会导致系统在用户态与内核态之间来回切换，严重影响锁的性能）
 **/
public class TestLoopLock {
    public TestLoopLock() {

    }

    private AtomicReference<Thread> cas = new AtomicReference<>();
    public void lock() {
        Thread current = Thread.currentThread();
        // 利用CAS
        while (!cas.compareAndSet(null, current)) {
            // DO nothing
        }
    }
    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }



    @Test
    public void test() {

    }
}
