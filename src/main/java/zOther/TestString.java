package zOther;


import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-06-03 10:21
 * @from
 *
 * 对象/数组 是引用传递
 * 基本数据类型 是值传递
 * *重点
 * String 也是引用传递,但是一般创建的String会在字符串常量池中存放(相当于值传递),传递的是对象的指针,当在另一个方法使用 var = "aaa" 时,实际上是把这个
 * 对象指针给改变了,而不是改变了指针指向的实际字符串
 * 可以debug时候看到 xxxx的val地址是466 而经过test方法的val='aaa' 后 val的地址变成了469,也就是方法内的val和外面的不是同一个指向地址
 * 所以val在方法内部的变化与外部无关
 *
 * String是引用类型。所以是引用传递
 * 但是String没有任何可以改变它状态的字段/方法，所有操作String的方法都是返回一个新的String对象，所以在函数里无论怎么改变String都是让它指向新的对象，
 * 从而对实参没有影响。
 * String是只读的。
 * String 内部是用final的char[] 进行实现,当new一个String或者直接 = 其他的字符串时候,实际是生成了另一个string对象,从而改变了指向地址
 * **/
public class TestString {
    public static void main(String[] args) {
        String val = new String("xxxx");
        char[] ch = {'a','b','c'};
        val = testString(val,ch);
        System.out.println(val);
        System.out.println(ch);

        String a = new String("qqqq");
        a.intern();
        String b = "qqqq";
        System.out.println(a == b);
    }
    public static String testString(String val ,char[] ch) {
        val = "aaa";
        ch[0] = 'c';
        return val;
    }
    
    @Test
    public void testStringx(){
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
        threadPoolExecutor.allowCoreThreadTimeOut(true); // 设置核心线程是否可以被回收
    }

}
