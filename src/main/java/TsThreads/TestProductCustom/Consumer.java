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
                System.out.println(Thread.currentThread().getName());
                while (productList.list.size()==0) {
                    productList.wait();
                }
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
