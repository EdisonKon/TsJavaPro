package TestCglib.CglibProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-06 09:53
 * @from
 **/
public class MyCglibProxyOther implements MethodInterceptor {
    public MyCglibProxyOther() {

    }

    /**
     *
     intercept方法的参数名并不是原生的参数名，我做了自己的调整，几个参数的含义为：

     Object表示要进行增强的对象
     Method表示拦截的方法
     Object[]数组表示参数列表，基本数据类型需要传入其包装类型，如int-->Integer、long-Long、double-->Double
     MethodProxy表示对方法的代理，invokeSuper方法表示对被代理对象方法的调用
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(method.getName()+"另一个准备"+System.currentTimeMillis());
        Object result = proxy.invokeSuper(obj,args);
        System.out.println(method.getName()+"另一个准备"+System.currentTimeMillis());

        return result;
    }




    @Test
    public void test() {

    }

}
