package TsDesignModel.factory.abstractfactory;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 10:45
 * @from
 **/
public class XiaomiRouter implements IrouterFactory{
    @Override
    public void wifi() {
        System.out.println("小米wifi");
    }

    @Override
    public void route() {
        System.out.println("小米route");
    }
}
