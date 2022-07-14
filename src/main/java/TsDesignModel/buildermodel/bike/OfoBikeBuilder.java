package TsDesignModel.buildermodel.bike;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:18
 * @from
 **/
public class OfoBikeBuilder extends Builder{
    @Override
    public void buildFrame() {
        bike.setFrame("铁皮架");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("橡胶");
    }

    @Override
    public Bike buildBike() {
        return bike;
    }
}
