package TsDesignModel.proxymodel.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 15:42
 * @from
 **/
public class CglibProxyFactory implements MethodInterceptor {

    //需要先生成一个被代理的类的对象
    IdoCloseDor closeDor = new IdoCloseDor();

    public IdoCloseDor getProxy(){
        //先创建Enhancer对象,该对象用于代理设置
        Enhancer enhancer = new Enhancer();
        //设置代理对象的父类
        enhancer.setSuperclass(IdoCloseDor.class);
        //设置回调函数, 也就是当前代理类对象  代理类工厂需要继承MethodIntercept类,实现intercept方法 用于回调
        enhancer.setCallback(this);
        //使用create方法创建代理类
        IdoCloseDor proxy = (IdoCloseDor) enhancer.create();
        return proxy;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是CGlib代理机器人,我来帮你");
        Object invoke = method.invoke(closeDor, objects);
        return invoke;
    }
}
