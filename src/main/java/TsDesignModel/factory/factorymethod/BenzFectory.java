package TsDesignModel.factory.factorymethod;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-19 21:11
 * @from
 **/
public class BenzFectory implements CarFactory {
    public static Benz getCat(){
        return new Benz();
    }
}