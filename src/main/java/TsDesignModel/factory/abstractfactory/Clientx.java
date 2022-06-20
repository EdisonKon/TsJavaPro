package TsDesignModel.factory.abstractfactory;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 10:49
 * @from
 **/
public class Clientx {
    public static void main(String[] args) {
        IProductFactory productFactory = new HuaweiProduct();
        IphoneFactory phone = productFactory.getPhone();
        phone.call();
        phone.sms();
        IrouterFactory router = productFactory.getRouter();
        router.wifi();
        router.route();

        IProductFactory productFactory2 = new XiaomiProduct();
        IphoneFactory phone2 = productFactory2.getPhone();
        phone2.call();
        phone2.sms();

        IrouterFactory router2 = productFactory2.getRouter();
        router2.wifi();
        router2.route();
    }
}
