package TestJVM.classloader;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2020-05-05 11:02
 * @from:
 *
 * 输出：
 * MyParent1 static block
 * hello,world
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

public class TestJVMLoad {
    public static void main(String[] args) {
        //并没有主动使用MyChild1 ,主动使用了parent
        System.out.println(MyChild1.str);
        /**
         * 输出
         * MyParent1 static block
         * hello,world
         */

        //主动使用MyChild1，要求初始化该类，并且其父类也需要初始化完成
//        System.out.println(MyChild1.str2);
        /**
         * 输出
         * MyParent1 static block
         * MyChild1 static block
         * welcome
         */
    }

}
class MyParent1{
    public static String str = "hello,world";

    static {
        System.out.println("MyParent1 static block");
    }
}

class MyChild1 extends MyParent1{
    public static String str2 = "welcome";
    static {
        System.out.println("MyChild1 static block");
    }
}

