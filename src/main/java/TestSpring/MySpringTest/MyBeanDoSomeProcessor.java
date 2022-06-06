package TestSpring.MySpringTest;


import TestSpring.MySpring.MyBeanProcessor;
import TestSpring.MySpring.MyComponment;
import TestSpring.MySpringTest.UserService;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-06 21:10
 * @from
 **/
@MyComponment
public class MyBeanDoSomeProcessor implements MyBeanProcessor {

    @Override
    public Object beforeBeanProcessor(String beanName, Object bean) {
        System.out.println(beanName + " before");
        return null;
    }

    @Override
    public Object afterBeanProcessor(String beanName, Object bean) {
        if ("userService".equals(beanName)) {
            ((UserService)bean).setBeanName(beanName + " 666");
        }
        return null;
    }
}
