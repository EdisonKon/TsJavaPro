package TsThreads;

import java.util.concurrent.Callable;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-01-24 11:12
 */


public class MyCallable implements Callable<String> {
    private long waitTime;
    public MyCallable(int timeInMillis){
        this.waitTime=timeInMillis;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }

}
