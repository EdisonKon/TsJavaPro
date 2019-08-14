package TestLoadSequence;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-13 09:18
 * @from
 **/
public class StaticTest {
    public static void main(String[] args) {
        staticFunction();
    }
    static StaticTest st = new StaticTest();

    static{
        System.out.println("1");
    }
    {
        System.out.println("2");
    }

    StaticTest() {
        System.out.println("3");
        System.out.println("a = " + a + "b = " + b);
    }

    public static void staticFunction(){
        System.out.println("4");
    }

    int a =110;
    static int b = 112;
    /**
     * 结果是
     * 2
     * 3
     * a = 110b = 0
     * 1
     * 4
     *
     * 因为static StaticTest st = new StaticTest(); 时候会把所有static的代码整合到一块去执行,那样的话 先执行new
     * new出来的代码 所有的static 已经移到到static StaticTest st = new StaticTest();里面,先new 的东西里面没有static
     * 的先执行.
     * 所以先执行这一块 ⤵
     *     {
     *         System.out.println("2");
     *     }
     *
     *     StaticTest() {
     *         System.out.println("3");
     *         System.out.println("a = " + a + "b = " + b);
     *         //此时
     *     }
     *
     *     {这一堆的static绑定一块了
     *         static StaticTest st = new StaticTest();
     *         static{
     *             System.out.println("1");
     *         }
     *         public static void staticFunction(){
     *             System.out.println("4");
     *         }
     *     }
     */
}
