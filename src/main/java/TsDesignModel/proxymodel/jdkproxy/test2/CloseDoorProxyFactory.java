package TsDesignModel.proxymodel.jdkproxy.test2;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 15:32
 * @from
 **/
public class CloseDoorProxyFactory{

    static CloseDoor closeDoor = new IdoCloseDor();


    public static CloseDoor getProxy() {
        CloseDoor proxyInstance = (CloseDoor) Proxy.newProxyInstance(
                closeDoor.getClass().getClassLoader(),
                closeDoor.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("我是代理机器人,我来帮你");
                    return method.invoke(closeDoor, args);
                });
        return proxyInstance;

    }
}
