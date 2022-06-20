package TsDesignModel.factory.abstractfactory;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 10:45
 * @from
 **/
public class HuaweiRouter implements IrouterFactory{
    @Override
    public void wifi() {
        System.out.println("华为wifi");
    }

    @Override
    public void route() {
        System.out.println("华为route");
    }
}
