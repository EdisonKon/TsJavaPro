package TsDesignModel.factory.abstractfactory;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 10:45
 * @from
 **/
public class XiaomiPhone implements IphoneFactory{
    @Override
    public void call() {
        System.out.println("小米call");
    }

    @Override
    public void sms() {
        System.out.println("小米sms");
    }
}
