package TsDesignModel.buildermodel.phone;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:43
 * @from
 **/
public class ApplePhoneBuilder extends PhoneBuilder{
    @Override
    public PhoneBuilder setCpu() {
        phoneBile.setCpu("A18");
        return this;
    }

    @Override
    public PhoneBuilder setMemory() {
        phoneBile.setMemory("Apple memory");
        return this;
    }

    @Override
    public PhoneBuilder setScreen() {
        phoneBile.setScreen("京东方");
        return this;
    }

    @Override
    public PhoneBuilder setButtery() {
        phoneBile.setBattery("超牛逼电池");
        return this;
    }
}
