package TestJVM.classloader;

import java.util.Random;
import java.util.UUID;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2020-05-05 11:02
 * @from:
 *
 * 接口本身就是final的，直接用常量值时候会放到调用类的常量池中
 * 当一个接口被初始化时候，并不要求父接口也初始化
 * 只有当一个接口真正使用到父接口的时候（如引用到了接口所定义的常量[动态常量如uuid]），这时才会初始化
 **/

public class TestJVMLoad5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.a);
    }

}

interface MyParent5{
    int a = new Random().nextInt(2);
    public static Thread thread= new Thread(){
        {
            System.out.println("parent5 invoke");
        }
    };

}

interface MyChild5 extends MyParent5{
    // 无所谓父类初始化与否
//        public static int b = 5;
    //必须父类要初始化完成，因为random属于动态初始化，无法在常量池中
    public static int b = new Random().nextInt(2);
}


