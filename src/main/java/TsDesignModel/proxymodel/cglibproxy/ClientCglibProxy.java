package TsDesignModel.proxymodel.cglibproxy;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 15:47
 * @from
 **/
public class ClientCglibProxy {
    public static void main(String[] args) {
        CglibProxyFactory proxyFactory = new CglibProxyFactory();
        IdoCloseDor proxy = proxyFactory.getProxy();
        proxy.closeDoor();
    }
}
