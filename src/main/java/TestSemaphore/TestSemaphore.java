package TestSemaphore;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-28 16:20
 * @from
 **/
public class TestSemaphore {
    public TestSemaphore(){
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        System.out.println(1111);
        MySemaphoreObjPool pool = new MySemaphoreObjPool(3,Entity.class);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> pool.getObjAndDo("say"));
            thread.start();
        }
        System.out.println("主线程完成");
    }



}
