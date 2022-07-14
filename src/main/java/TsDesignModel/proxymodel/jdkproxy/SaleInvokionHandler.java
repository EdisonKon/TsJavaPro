package TsDesignModel.proxymodel.jdkproxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 14:32
 * @from
 **/
public class SaleInvokionHandler implements InvocationHandler {

    private SaleComputer saleComputer;

    public SaleInvokionHandler(SaleComputer saleComputer) {
        this.saleComputer = saleComputer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被我代理了");
        return method.invoke(saleComputer,args);
    }
}
