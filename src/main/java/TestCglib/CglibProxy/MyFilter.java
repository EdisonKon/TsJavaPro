package TestCglib.CglibProxy;

import net.sf.cglib.proxy.CallbackFilter;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-06 11:22
 * @from
 **/
public class MyFilter implements CallbackFilter {
    public MyFilter() {

    }

    @Override
    public int accept(Method method) {
        if("sayDushu".equals(method.getName())){
            return 0;
        }
        else if("saySomething".equals(method.getName())){
            return 1;
        }
        else{
            return 2;
        }

    }

    @Test
    public void test() {

    }
}
