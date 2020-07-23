package TsStatic;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-07-23 23:39
 * @from
 **/
public class TsTest {
    public TsTest() {

    }


    @Test
    public void test() {
        TsStatic ts = new TsStatic();
        System.out.println(TsStatic.df);
    }

    public static void main(String[] args) {
        TsStatic ts = new TsStatic();
        System.out.println(TsStatic.df);
    }
}
