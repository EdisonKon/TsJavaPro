package TsDesignModel.adaptermodel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 17:20
 * @from
 * 适配器处理, 实现默认的接口,在默认的接口里调用目标接口
 **/
public class AdapterHandler implements CommonInterface {

    NeedInvokeClass needInvokeClass = new NeedInvokeClass();

    @Override
    public void invokeNeed() {
        needInvokeClass.needInvoke();
    }
}
