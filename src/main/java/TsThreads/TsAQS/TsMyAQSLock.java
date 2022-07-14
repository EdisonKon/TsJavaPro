package TsThreads.TsAQS;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-12 12:25
 * @from 实现一个AQS锁 同时可以有三个线程访问锁资源 每隔1s打印3个数
 **/
public class TsMyAQSLock extends AbstractQueuedSynchronizer {

    public TsMyAQSLock(int count) {
        setState(count);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        for (;;){
            int cur = getState();
            int newc = cur - arg;
            // 注意这里: 当newc 小于0的时候, AQS中源码写的是如果小于0则在doAcquireShared方法中加入到等待队列中,
            // 继续尝试tryAcquire,直到可以设置成功且newc>=0的情况才获取到锁
            if(newc <0 || compareAndSetState(cur,newc)){
                System.out.println(newc);
                return newc;
            }
        }
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        for (;;){
            int cur = getState();
            int newc = cur + arg;
            if(compareAndSetState(cur,newc)){
                return true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final TsMyAQSLock aqsLock = new TsMyAQSLock(3);
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                aqsLock.acquireShared(1);
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+" -> ok");
                } catch (InterruptedException e) {
                }finally {
                    aqsLock.releaseShared(1);
                }
            },"当前"+i).start();
        }
        TimeUnit.SECONDS.sleep(100);
    }
}
