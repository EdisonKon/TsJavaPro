package TsDesignModel.decoratormodel;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 19:40
 * @from 快餐装饰器
 **/
public abstract class FastFoodDecorator extends FastFood{

    private FastFood fastFood;

    public FastFood getFastFood() {
        return fastFood;
    }

    public void setFastFood(FastFood fastFood) {
        this.fastFood = fastFood;
    }

    public FastFoodDecorator(FastFood fastFood, int price, String desc) {
        super(price, desc);
        this.fastFood = fastFood;
    }

}
