package TsDesignModel.singleton;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 15:28
 * @from
 * 静态内部类单例模式 相当于在类加载时就进行加载内容
 * 会被 反射调用 创建新的对象
 **/
public class Holder {
    // 私有构造器
    private Holder() {
    }


    private static class InnerHolder{
        private static final Holder INSTANCE = new Holder();
    }

    public static Holder getHolderInstance(){
        return InnerHolder.INSTANCE;
    }

}
