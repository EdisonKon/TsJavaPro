package TsDesignModel.proxymodel.staticproxy;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 14:14
 * @from
 **/
public class ComputerProxy implements SaleComputer {

    LenovoShop shop = new LenovoShop();

    @Override
    public void saleComputer() {
        System.out.println("代理点卖电脑");
        shop.saleComputer();
    }
}
