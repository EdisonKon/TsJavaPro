package TsDesignModel.adaptermodel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 17:18
 * @from
 **/
public class TestAdapter {
    public static void main(String[] args) {


        CommonInterface normalInvokeClass = new NormalInvokeClass();
        normalInvokeClass.invokeNeed();
        //只调用 CommonInterface 中的 invokeNeed,即可调用adapter中的目标类方法
        CommonInterface anInterface = new AdapterHandler();
        anInterface.invokeNeed();
    }
}
