package TsDesignModel.factory.abstractfactory;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 10:45
 * @from
 **/
public class HuaweiProduct implements IProductFactory{
    @Override
    public IrouterFactory getRouter() {
        return new HuaweiRouter();
    }

    @Override
    public IphoneFactory getPhone() {
        return new HuaweiPhone();
    }
}
