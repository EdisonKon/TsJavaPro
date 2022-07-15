package TsDesignModel.strategymodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 12:59
 * @from 策略类之间可以自由切换
 *
 * 由于策略类都实现同一个接口，所以使它们之间可以自由切换。
 *
 * 易于扩展
 *
 * 增加一个新的策略只需要添加一个具体的策略类即可，基本不需要改变原有的代码，符合“开闭原则“
 *
 * 避免使用多重条件选择语句（if else），充分体现面向对象设计思想。
 **/
public class SalesMan {
    //持有抽象策略角色的引用
    private Strategy strategy;

    public SalesMan(Strategy strategy) {
        this.strategy = strategy;
    }

    //向客户展示促销活动
    public void salesManShow(){
        strategy.show();
    }

    public static void main(String[] args) {
        SalesMan salesMan = new SalesMan(new StrategyA());
        salesMan.salesManShow();
    }
}
