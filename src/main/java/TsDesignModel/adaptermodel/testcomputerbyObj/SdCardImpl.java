package TsDesignModel.adaptermodel.testcomputerbyObj;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 18:46
 * @from
 **/
public class SdCardImpl implements SdCard {
    @Override
    public void readSd() {
        System.out.println("我在读 sd catd");
    }
}
