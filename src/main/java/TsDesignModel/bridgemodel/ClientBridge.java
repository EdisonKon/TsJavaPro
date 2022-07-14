package TsDesignModel.bridgemodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 20:23
 * @from
 **/
public class ClientBridge {
    public static void main(String[] args) {
        OpearSystem opearSystem = new MacSystem(new AviFile());
        opearSystem.playFile("你好中国");
    }
}
