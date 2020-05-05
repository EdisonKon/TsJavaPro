package TestJVM.classloader;

import java.util.Random;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2020-05-05 11:02
 * @from:
 *
 * 准备阶段，赋值为初始化值（int=0），初始化赋值为应为值
 **/

public class TestJVMLoad6 {
    public static void main(String[] args) {
        //对类的主动使用，所以会初始化该类
        Singleton singleton = Singleton.getInstance();
        //输出为1，0
        System.out.println("c1:"+Singleton.counter1);
        System.out.println("c2:"+Singleton.counter2);
    }

}

class Singleton{
    public static int counter1;

//    public static int counter2 = 0;

    //singleton 需要从上到下初始化该类
    private static Singleton singleton = new Singleton();

    //执行完该构造方法后，1和2的值都是1
    public Singleton() {
        counter1++;
        counter2++;
        System.out.println("c1:"+counter1);
        System.out.println("c2:"+counter2);
    }

    //现在又重新给2赋值为0
    public static int counter2 = 0;

    public static Singleton getInstance(){
        //对类的主动使用，所以会初始化该类，然后需要的是singleton
        return singleton;
    }
}


