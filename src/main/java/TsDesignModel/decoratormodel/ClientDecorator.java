package TsDesignModel.decoratormodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 19:47
 * @from
 **/
public class ClientDecorator {
    public static void main(String[] args) {
        FastFood fastFood = new FriedNoodle();
        System.out.println(fastFood.getDesc());
        fastFood = new DecoratorBacon(fastFood);
        System.out.println(fastFood.getDesc() + "  " + fastFood.cost() + " 元");
    }
}
