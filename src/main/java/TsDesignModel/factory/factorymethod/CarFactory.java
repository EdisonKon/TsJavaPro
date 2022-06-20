package TsDesignModel.factory.factorymethod;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-19 21:10
 * @from 工厂方法模式
 *  新增一个车就需要增加车的类和车的工厂类
 *
 **/
public interface CarFactory {
    static Car getCat() {
        return null;
    }

}
