package TsThreads.TestProductCustom;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-07-30 17:03
 * @from
 **/
public class Producter {

    private final ProductList productList;

    public Producter(ProductList productList) {
        this.productList = productList;
    }

    public void produce() throws InterruptedException {
        synchronized (productList) {
            System.out.println("jsppp"+productList);
            while (true) {
                System.out.println(Thread.currentThread().getName()  + "生产者");
                while (productList.list.size() >= productList.limit) {
                    productList.wait();
                }
                Thread.sleep(1000);
                productList.list.add("product");
                System.out.println("生产成功" + productList.list.size());
                productList.notifyAll();
            }
        }
    }


    @Test
    public void test() {

    }
}
