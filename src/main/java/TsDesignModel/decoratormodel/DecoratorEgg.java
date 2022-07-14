package TsDesignModel.decoratormodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 19:41
 * @from 加鸡蛋
 **/
public class DecoratorEgg extends FastFoodDecorator{

    public DecoratorEgg(FastFood fastFood) {
        super(fastFood,2,"加鸡蛋");
    }

    @Override
    public float cost() {
        return getPrice()+ getFastFood().getPrice();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }
}
