package TsDesignModel.factory.factorymethod;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-19 21:16
 * @from
 **/
public class TestMethodFactory {
    public static void main(String[] args) {
        Car car = BenzFectory.getCat();
        car.name();
    }

}
