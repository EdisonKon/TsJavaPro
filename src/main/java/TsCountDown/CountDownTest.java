package TsCountDown;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-01-24 16:52
 */


public class CountDownTest {
    private int count;
    private CountDownLatch countDownLatch;

    public CountDownTest(int count, CountDownLatch countDownLatch) {
        this.count = count;
        this.countDownLatch = countDownLatch;
    }

    public synchronized void inc() {
        this.count++;
        countDownLatch.countDown();
    }

    public int get(){
        return count;
    }

}
