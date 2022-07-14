package TsDesignModel.decoratormodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 19:38
 * @from 炒饭类
 **/
public class FriedRice extends FastFood{

    public FriedRice() {
        super(10, "牛逼炒饭");
    }

    @Override
    public float cost() {
        return getPrice();
    }


}
