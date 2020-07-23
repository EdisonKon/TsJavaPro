package TsStatic;

import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-07-23 23:11
 * @from 测试static 静态变量赋值影响
 * 当静态变量在静态代码块里,那么就会指向同一个对象,但是如果是静态方法或者其他方法里,那就会指向不同的对象
 *
 **/
public class TsStatic {
    static SimpleDateFormat sdf = new SimpleDateFormat("yy");
    static SimpleDateFormat sdf2;
    static Object df;
    public TsStatic() {

    }

//    static void setSdf(){
    static {
        sdf2 = new SimpleDateFormat("yyyy");
        df = new Object();
    }


    @Test
    public void test() throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep((long)(Math.random()*3000+1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            setSdf();
            TsStatic tsStatic = new TsStatic();
            System.out.println(Thread.currentThread().getName()+":"+tsStatic.sdf);
            System.out.println(Thread.currentThread().getName()+":"+tsStatic.sdf2);
            System.out.println(Thread.currentThread().getName()+":"+tsStatic.df);
        });
        Thread t2 = new Thread(()->{
            try {
                Thread.sleep((long)(Math.random()*3000+1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            setSdf();
            TsStatic tsStatic = new TsStatic();
            System.out.println(Thread.currentThread().getName()+":"+tsStatic.sdf);
            System.out.println(Thread.currentThread().getName()+":"+tsStatic.sdf2);
            System.out.println(Thread.currentThread().getName()+":"+tsStatic.df);
        });
        Thread t3 = new Thread(()->{
            try {
                Thread.sleep((long)(Math.random()*3000+1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            setSdf();
            TsStatic tsStatic = new TsStatic();
            System.out.println(Thread.currentThread().getName()+":"+tsStatic.sdf);
            System.out.println(Thread.currentThread().getName()+":"+tsStatic.sdf2);
            System.out.println(Thread.currentThread().getName()+":"+tsStatic.df);
        });


        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
