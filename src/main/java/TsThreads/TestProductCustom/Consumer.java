package TsThreads.TestProductCustom;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-07-30 17:04
 * @from
 **/
public class Consumer {

    private final ProductList productList;

    public Consumer(ProductList productList) {
        this.productList = productList;
    }

    public void consume() throws InterruptedException {
        synchronized (productList) {
            System.out.println("jsccc"+productList);
            while (true) {
                System.out.println(Thread.currentThread().getName() + "消费者");
                while (productList.list.size()==0) {
                    // wait()之后的线程 会进入条件等待队列, 当收到唤醒信号,重新进入获取锁的等待队列, 但是代码的执行是从wait()之后继续执行
                    productList.wait();
                }
                Thread.sleep(1000);
                productList.list.remove("product");
                System.out.println("消费成功");
                productList.notifyAll();
            }
        }
    }


    @Test
    public void test() {

    }
}
