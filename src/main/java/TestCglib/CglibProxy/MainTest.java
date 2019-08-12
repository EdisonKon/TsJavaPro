package TestCglib.CglibProxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-06 11:23
 * @from
 *
 * JDK动态代理
 * 优点
 * 不依赖第三方jar包, 使用方便
 * 随着JDK的升级，JDK动态代理的性能在稳步提升
 * 缺点
 * 只能代理实现了接口的类
 * 执行速度较慢
 * 适用场景
 * 如果你的程序需要频繁、反复地创建代理对象，则JDK动态代理在性能上更占优。
 *
 * cglib
 * 优点
 * 由于是动态生成字节码实现代理，因此代理对象的执行速度较快, 约为JDK动态代理的1.5 ~ 2倍
 * 可以代理没有实现接口的对象
 * 缺点
 * 不能代理final类
 * 动态生成字节码虽然执行较快，但是生成速度很慢，根据网上一些人的测试结果，cglib创建代理对象的速度要比JDK慢10 ~ 15倍。
 * 适用场景
 * 不需要频繁创建代理对象的应用，如Spring中默认的单例bean，只需要在容器启动时生成一次代理对象。
 *
 **/
public class MainTest {
    public MainTest() {

    }


    @Test
    public void test() {
        MyCglibProxy proxy = new MyCglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BaseClass.class);
        enhancer.setCallback(proxy);
        BaseClass baseClass = (BaseClass)enhancer.create();
        baseClass.sayDushu();
        baseClass.saySomething();
    }

    @Test
    public void test2() {
        MyCglibProxy proxy = new MyCglibProxy();
        MyCglibProxyOther proxyano = new MyCglibProxyOther();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BaseClass.class);
        /**
         * NoOp.INSTANCE 表示空的callback
         * 也就是自定义的filter中
         * 0位 的方法使用第一个proxy
         * 1位 的方法使用第二个proxy
         * 2位 的方法使用默认无callback
         */
        enhancer.setCallbacks(new Callback[]{proxy,proxyano, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new MyFilter());
        BaseClass baseClass = (BaseClass)enhancer.create();
        baseClass.sayDushu();
        baseClass.saySomething();
        baseClass.test();
    }
}
