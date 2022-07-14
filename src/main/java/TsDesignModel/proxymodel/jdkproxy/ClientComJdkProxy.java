package TsDesignModel.proxymodel.jdkproxy;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 14:38
 * @from
 **/
public class ClientComJdkProxy {
    public static void main(String[] args) {
        SaleComputer proxy = ProxyFactory.getProxy();
        proxy.saleComputer();
    }
}
