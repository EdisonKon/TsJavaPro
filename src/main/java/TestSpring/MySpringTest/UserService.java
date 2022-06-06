package TestSpring.MySpringTest;

import TestSpring.MySpring.*;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-06 16:22
 * @from
 **/
@MyComponment
public class UserService implements BeanNameAware, MyInitializingBean {

    @MyAutowired
    private OrderService orderService;

    private String beanName;

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        System.out.println(beanName);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet");
    }


    public void test(){
        System.out.println(orderService);
        System.out.println(beanName);
    }
}
