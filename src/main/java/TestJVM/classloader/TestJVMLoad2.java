package TestJVM.classloader;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2020-05-05 11:02
 * @from:
 *
 * 对于静态字段来说，只有直接定义了该字段的类才会被初始化
 *
 * 对某一个要使用的类，要求初始化该类，并且其父类也需要初始化完成
 *
 * -XX:+TraceClassLoading 输出类加载信息
 *
 * -XX:+<option> 表示打开选项
 * -XX:-<option> 表示关闭选项
 * -XX:<option>=<value> 表示将某个选项值设置为 value
 **/

public class TestJVMLoad2 {
    public static void main(String[] args) {
        //该静态变量加不加final 结果区别 需不需要初始化该类
        //final 直接输出str
        //无final 输出初始化类，然后输出str
        //final会使该常量在 编译阶段 放入到调用方法所在的类的常量池中（也就是当前调用方法Main它的类：TestJVMLoad2的常量池，所以跟MyParent2没啥关系），所以不会初始化MyParent2类
        //本质上不在定义该常量的MyParent2这个类里，所以并没有直接引用到该类
        //甚至可以将MyParent2.class删除，仍可运行
        System.out.println(MyParent2.str);

        //主动使用MyChild1，要求初始化该类，并且其父类也需要初始化完成
//        System.out.println(MyChild1.str2);
        /**
         * 输出
         * MyParent2 static block
         * MyChild2 static block
         * hello,world
         */
    }

}

class MyParent2{
    //该静态变量加不加final 结果区别 需不需要初始化该类
    //final 直接输出str
    //无final 输出初始化类，然后输出str
    //final会使该常量 放入到调用方法所在的类的常量池中，所以不会初始化该类
    public static String str = "hello,world";

    static {
        System.out.println("MyParent2 static block");
    }
}

class MyChild2 extends MyParent2{
    public static String str2 = "welcome";
    static {
        System.out.println("MyChild2 static block");
    }
}

