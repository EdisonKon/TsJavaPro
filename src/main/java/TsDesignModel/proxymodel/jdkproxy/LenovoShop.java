package TsDesignModel.proxymodel.jdkproxy;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 14:20
 * @from
 **/
public class LenovoShop implements SaleComputer {
    @Override
    public void saleComputer() {
        System.out.println("卖出联想电脑");
    }
}
