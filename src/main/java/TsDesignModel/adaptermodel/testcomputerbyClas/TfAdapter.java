package TsDesignModel.adaptermodel.testcomputerbyClas;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 18:48
 * @from
 **/
public class TfAdapter extends TfCardImpl implements SdCard {

    @Override
    public void readSd() {
        System.out.println("我是适配器,帮助Tf转成SD");
        readTf();
    }

    @Override
    public void readTf() {
        super.readTf();
    }
}
