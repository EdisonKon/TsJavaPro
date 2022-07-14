package TsDesignModel.decoratormodel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 19:41
 * @from 加培根
 **/
public class DecoratorBacon extends FastFoodDecorator{

    public DecoratorBacon(FastFood fastFood) {
        super(fastFood,4,"加培根");
    }

    @Override
    public float cost() {
        return getPrice() + getFastFood().getPrice();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }
}
