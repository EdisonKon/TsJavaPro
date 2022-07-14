package TsDesignModel.buildermodel.bike;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:20
 * @from 指挥者类
 **/
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    // 组装自行车
    public Bike build(){
        builder.buildFrame();
        builder.buildSeat();
        return builder.buildBike();
    }
}
