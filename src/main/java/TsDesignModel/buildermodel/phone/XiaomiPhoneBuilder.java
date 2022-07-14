package TsDesignModel.buildermodel.phone;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:43
 * @from
 **/
public class XiaomiPhoneBuilder extends PhoneBuilder{
    @Override
    public PhoneBuilder setCpu() {
        phoneBile.setCpu("骁龙X1");
        return this;
    }

    @Override
    public PhoneBuilder setMemory() {
        phoneBile.setMemory("Xiaomi memory");
        return this;
    }

    @Override
    public PhoneBuilder setScreen() {
        phoneBile.setScreen("小米屏幕");
        return this;
    }

    @Override
    public PhoneBuilder setButtery() {
        phoneBile.setBattery("小米电池");
        return this;
    }
}
