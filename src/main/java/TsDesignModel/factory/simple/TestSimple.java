package TsDesignModel.factory.simple;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-19 21:16
 * @from
 **/
public class TestSimple {
    public static void main(String[] args) {
        Car car = SimpleFactory.getCat("t");
        car.name();
        Car car2 = SimpleFactory.getCat("b");
        car2.name();
    }

}
