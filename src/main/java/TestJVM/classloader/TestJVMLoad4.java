package TestJVM.classloader;

import java.util.UUID;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2020-05-05 11:02
 * @from:
 *
 * 对于数组实例来说，其类型是由jvm在运行期间动态生成的，表示为class [LTestJVM.classloader.MyParent4;
 * 这种动态生成的类型，其父类就是Object
 * 其父类一般就是降为一级数组时候的component，实际就是一维数组的类型
 **/

public class TestJVMLoad4 {
    public static void main(String[] args) {
        //输出：
        //MyParent4 static block
        //程序运行时，首次使用，会导致主动使用存在该声明的类，并初始化它（MyParent3）
        MyParent4 myParent4 = new MyParent4();
        System.out.println(myParent4.getClass());
        //不会再次初始化
        //无输出
        MyParent4 myParentx = new MyParent4();
        //无输出
        //只是代表申请了一段内存空间，并无主动使用
        MyParent4[] arr = new MyParent4[1];
        //class [LTestJVM.classloader.MyParent4;
        System.out.println(arr.getClass());
        //class java.lang.Object
        System.out.println(arr.getClass().getSuperclass());
        //无输出
        //只是代表申请了一段内存空间，并无主动使用
        MyParent4[][] arr2 = new MyParent4[1][1];
        //class java.lang.Object
        System.out.println(arr2.getClass().getSuperclass());

        int[] ints = new int[1];
        //[I
        System.out.println(arr2.getClass());
        //class java.lang.Object
        System.out.println(arr2.getClass().getSuperclass());
    }

}

class MyParent4{

    static {
        System.out.println("MyParent4 static block");
    }
}

