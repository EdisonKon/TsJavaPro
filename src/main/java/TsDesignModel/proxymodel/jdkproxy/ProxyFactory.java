package TsDesignModel.proxymodel.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 14:14
 * @from
 **/
public class ProxyFactory {

    private static LenovoShop lenovoShop = new LenovoShop();

    public static SaleComputer getProxy(){
        SaleComputer saleComputer = (SaleComputer) Proxy.newProxyInstance(
                lenovoShop.getClass().getClassLoader(),
                lenovoShop.getClass().getInterfaces(),
                new SaleInvokionHandler(lenovoShop));
        return saleComputer;
    }
}
