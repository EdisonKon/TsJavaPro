package TsDesignModel.proxymodel.jdkproxy.test2;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 15:34
 * @from
 **/
public class ClientCloseDoor {
    public static void main(String[] args) {
        CloseDoor proxy = CloseDoorProxyFactory.getProxy();
        proxy.closeDoor();
    }
}
