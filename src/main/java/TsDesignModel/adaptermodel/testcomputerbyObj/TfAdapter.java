package TsDesignModel.adaptermodel.testcomputerbyObj;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 18:48
 * @from
 **/
public class TfAdapter implements SdCard {

    private TfCard tfCard;
    public TfAdapter(TfCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public void readSd() {
        System.out.println("我是适配器,帮助Tf转成SD");
        tfCard.readTf();
    }

}
