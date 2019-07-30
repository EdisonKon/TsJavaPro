package TsThreads.TestProductCustom;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-07-30 17:04
 * @from
 **/
public class Main {

    public static void main(String[] args) {
        ProductList productList = new ProductList();
        Thread p = new Thread(() -> {
            try {
                new Producter(productList).produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread c = new Thread(() -> {
            try {
                new Consumer(productList).consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });




        c.start();
        p.start();

    }
}