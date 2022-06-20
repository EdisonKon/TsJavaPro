package TsDesignModel.factory.factorymethod;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-19 21:11
 * @from
 **/
public class TeslaFectory implements CarFactory  {
    public static Tesla getCat() {
        return new Tesla();
    }
}
