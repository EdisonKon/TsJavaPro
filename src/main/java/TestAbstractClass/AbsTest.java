package TestAbstractClass;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-05-09 14:41
 * @from
 **/
public class AbsTest {
    public AbsTest() {

    }


    @Test
    public void test() {

    }

    public static void main(String[] args) {
        AbsImpl abs = new AbsImpl("123");
        System.out.println(abs);
        CQueue cQueue = new CQueue("1234");
        System.out.println(cQueue);
    }
}
