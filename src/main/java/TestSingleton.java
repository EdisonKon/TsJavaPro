import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-28 10:12
 * @from
 **/
public class TestSingleton {
    private volatile TestSingleton instant;
    public TestSingleton() {
    }

    public TestSingleton getInstant() {
        if(instant == null){
            synchronized (TestSingleton.class){
                if(instant == null){
                    instant = new TestSingleton();
                }
            }
        }
        return instant;
    }

    @Test
    public void test() {
        getInstant();
    }
}
