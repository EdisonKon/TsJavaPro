package TsThreads.TestReentant;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-07-25 15:39
 * @from
 **/
public class MyThread extends Thread{
    private TestReentantLock testReentantLock;
    public MyThread(TestReentantLock testReentantLock){
        super();
        this.testReentantLock = testReentantLock;
    }
//    @Override
//    public void run() {
//        testReentantLock.testAwait2();
//    }

    @Override
    public void run() {
        testReentantLock.testAwait2();
    }


    @Test
    public void test() {

    }
}
