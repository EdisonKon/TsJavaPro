package TestJVM.classloader;

import java.util.UUID;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2020-05-05 11:02
 * @from:
 *
 **/

public class TestJVMLoad3 {
    public static void main(String[] args) {
        //编译期间无法确定str的值，那么该值就无法被放到调用方法的类的常量池中
        //所以程序运行时，会导致主动使用存在该声明的类，并出示化它（MyParent3）
        System.out.println(MyParent3.str);
        //输出：
        //MyParent3 static block
        //UUID ： b3a4d6aa-0b77-42ae-ab06-f948f2ec3315
    }

}

class MyParent3{
    //编译期间无法知道UUID的值，所以需要优先编译初始化该类，所以static块会被输出
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent3 static block");
    }
}

