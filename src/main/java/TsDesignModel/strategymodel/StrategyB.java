package TsDesignModel.strategymodel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 12:58
 * @from
 **/
public class StrategyB implements Strategy{
    @Override
    public void show() {
        System.out.println("满200元减50元");
    }
}
