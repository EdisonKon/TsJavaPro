package TsDesignModel.buildermodel.bike;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:23
 * @from
 **/
public class ClientBuilder {
    public static void main(String[] args) {
        Director director = new Director(new MobiBikeBuilder());
        Bike bike = director.build();
        System.out.println(bike);

//      直接在builder抽象类中进行组装, build()
        Builder builder = new OfoBikeBuilder();
        Bike build = builder.build();
        System.out.println(build);
    }
}
