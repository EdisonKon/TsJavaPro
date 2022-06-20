package TsDesignModel.adaptermodel;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 17:16
 * @from
 *  正常可以直接调用的类和方法
 **/
public class NormalInvokeClass implements CommonInterface {

    @Override
    public void invokeNeed() {
        System.out.println("正常可以直接调用的方法");
    }
}
