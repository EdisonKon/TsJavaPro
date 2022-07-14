package TsDesignModel.bridgemodel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 20:18
 * @from
 **/
public class RmvbFile implements VideoFile{

    @Override
    public void decode(String filename) {
        System.out.println("解码RMVB格式文件:" + filename);
    }
}
