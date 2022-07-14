package TsDesignModel.adaptermodel.testcomputerbyClas;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 18:54
 * @from 只能读取sd卡的电脑
 **/
public class ReadSdComputer {

    public void read(SdCard sdCard){
        System.out.println("我在读取SD卡的内容");
        sdCard.readSd();
    }

    public static void main(String[] args) {
        ReadSdComputer computer = new ReadSdComputer();
        computer.read(new TfAdapter());
    }
}
