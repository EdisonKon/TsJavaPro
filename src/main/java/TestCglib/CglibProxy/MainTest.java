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
