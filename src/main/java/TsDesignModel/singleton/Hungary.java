package TsDesignModel.singleton;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 15:14
 * @from
 **/
public class Hungary {
    // 单例必须无参构造需要私有
    private Hungary(){

    }
    // 饿汉式就是类一加载就给分配好内存
    private static final Hungary HUNGARY = new Hungary();

    public Hungary getInstance(){
        return HUNGARY;
    }

}
