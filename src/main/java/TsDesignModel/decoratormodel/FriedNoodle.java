package TsDesignModel.decoratormodel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 19:38
 * @from 炒面类
 **/
public class FriedNoodle extends FastFood{

    public FriedNoodle() {
        super(11, "牛逼炒面");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
