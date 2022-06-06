package TestSpring.MySpringTest;

import TestSpring.MySpring.ApplicationContext;
import TestSpring.MySpring.MyScan;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-06 16:21
 * @from
 **/
@MyScan("TestSpring.MySpringTest")
public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(TestSpring.class);
        // 测试单例
        Object o = context.getBean("userService");
        Object o2 = context.getBean("userService");
        System.out.println(o);
        System.out.println(o2);
        // 测试autowired
        UserService userService = (UserService) context.getBean("userService");
        userService.test();
    }
}
