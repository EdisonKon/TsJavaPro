package TsDesignModel.buildermodel.bike;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:16
 * @from 抽象建造者
 **/
public abstract class Builder {
    //自行车的组件还没组装
    protected Bike bike = new Bike();

    public abstract void buildFrame();

    public abstract void buildSeat();

    public abstract Bike buildBike();


    //还有一种方式是不需要指挥者,直接在builder里写build进行指挥组装
    public Bike build(){
        buildFrame();
        buildSeat();
        buildBike();
        return bike;
    }

}
