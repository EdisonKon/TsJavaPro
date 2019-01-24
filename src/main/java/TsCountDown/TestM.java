package TsCountDown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-01-24 16:53
 */


public class TestM {
    public static void main(String[] args) {
        int count = 80000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        CountDownTest ai = new CountDownTest(0, countDownLatch);

        for (int i = 0; i < count; i++) {
            Thread t = new Thread(() -> {
                ai.inc();
            });
            t.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ai.get());
    }
}
