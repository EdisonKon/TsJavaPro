package TsDesignModel.bridgemodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 20:18
 * @from
 **/
public class AviFile implements VideoFile{

    @Override
    public void decode(String filename) {
        System.out.println("解码AVI格式文件:" + filename);
    }
}
