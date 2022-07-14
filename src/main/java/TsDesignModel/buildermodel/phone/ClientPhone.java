package TsDesignModel.buildermodel.phone;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:46
 * @from
 **/
public class ClientPhone {
    public static void main(String[] args) {
        PhoneBuilder builder = new ApplePhoneBuilder();
        PhoneBile build = builder.build();
        System.out.println(build);
    }
}
