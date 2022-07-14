package TsDesignModel.buildermodel.bike;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:18
 * @from
 **/
public class MobiBikeBuilder extends Builder{
    @Override
    public void buildFrame() {
        bike.setFrame("碳纤维");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("真皮");
    }

    @Override
    public Bike buildBike() {
        return bike;
    }
}
