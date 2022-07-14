package TsDesignModel.buildermodel.phone;



/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:39
 * @from
 **/
public abstract class PhoneBuilder {

    protected PhoneBile phoneBile = new PhoneBile();

    public abstract PhoneBuilder setCpu();
    public abstract PhoneBuilder setMemory();
    public abstract PhoneBuilder setScreen();
    public abstract PhoneBuilder setButtery();

    public PhoneBile build(){
        return setCpu().setMemory().setScreen().setButtery().phoneBile;
    }


}
