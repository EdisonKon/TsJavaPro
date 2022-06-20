package TsDesignModel.singleton;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 15:16
 * @from
 *
 * DCL double check lock 懒汉单例
 *
 **/
public class LazyMan {
    // 懒汉式单例 也需要把构造函数 私有化
    private LazyMan() {

    }
    // 内存可见性和禁止指令重排
    // new LazyMan() 这个不是原子操作
    // 1.先定义对象, 2.然后分配内存 , 3.然后设置指针指向内存
    // 属于三步,通过volatile禁止这三步重排序
    private volatile static LazyMan LAZY_MAN;

    public static LazyMan getInstance() {
        //双判空检测
        if(LAZY_MAN == null){
            synchronized (LazyMan.class){
                if(LAZY_MAN == null){
                    LAZY_MAN = new LazyMan();
                }
            }
        }
        return LAZY_MAN;
    }
}
