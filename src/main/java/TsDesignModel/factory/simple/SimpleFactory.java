package TsDesignModel.factory.simple;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-19 21:10
 * @from 简单工厂模式 / 静态工厂模式
 * 不足: 新增一个种类的车 就需要修改代码
 *
 **/
public class SimpleFactory {
    public static Car getCat(String name){
        if("t".equals(name)){
            return new Tesla();
        }else if("b".equals(name)){
            return new Benz();
        }
        return null;
    }

}
