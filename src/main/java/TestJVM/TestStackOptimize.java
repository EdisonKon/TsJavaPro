package TestJVM;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-01-09 09:54
 * @from https://mp.weixin.qq.com/s/WQQScCVbHBkIPD1a5852jA
 * 为什么变量不用了要赋值null ???
 * jvm的局部变量表优化
 * 代码1的部分,执行了gc但是并不会回收pac的内存,那么是为什么呢?
 * 代码2的部分,执行pac=null后gc会回收内存
 * 代码3的部分,执行bx=1后依然会回收内存,是因为jvm的运行时栈里的局部变量表会有优化
 * 具体优化:
 *  代码1:
 *  第一个局部变量args 索引是 0
 *  第2个局部变量pac 索引是 1
 *  代码2/3:
 *  第一个局部变量args 索引是 0
 *  第2个局部变量pac 索引是 1
 *  第3个局部变量pac=null或者bx=1会把之前使用完了的索引更新 那么第三个变量在局部变量表里索引是 1,就会替代pac
 *  那么也就会回收掉pac的内存,此操作是在编译时候就编译好的
 *  (jvm运行栈的局部变量表优化)
 **/
public class TestStackOptimize {
    public TestStackOptimize() {

    }

    // 1
    public static void main(String[] args) {
        if(true){
            Byte[] pac = new Byte[1024];
            System.out.println(pac);
        }
        System.gc();
    }

    // 2
    public static void main2(String[] args) {
        if(true){
            Byte[] pac = new Byte[1024];
            System.out.println(pac);
            pac = null;
        }
        System.gc();
    }

    // 3
    public static void main3(String[] args) {
        if(true){
            Byte[] pac = new Byte[1024];
            System.out.println(pac);
        }
        int bx = 1;
        System.gc();
    }

    @Test
    public void test() {

    }
}
