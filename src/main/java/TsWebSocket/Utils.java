package TsWebSocket;

import TsWebSocket.objs.BackObj;
import com.alibaba.fastjson.JSON;
import org.junit.Test;


/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-11 15:25
 * @from
 **/
public class Utils {
    public Utils() {

    }


    public static String getZeroStr(String param){
        for (int i = param.length(); i < 8; i++) {
            param= "0"+param;
        }

        return param;
    }

    public static String getSpaceStr(String param){
        for (int i = param.length(); i < 64; i++) {
            param= param + " ";
        }

        return param;
    }

    public static BackObj getContent(String param){
        String parms = param.substring(64);
        BackObj backObj = JSON.parseObject(parms,BackObj.class);
        return backObj;
    }



    @Test
    public void test() {
        getContent("CT40010000021700000001                                          {\"method\":\"initSession\",\"object\":\"cti\",\"params\":[\"\",\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36;Tue Feb 11 15:40:06 CST 2020\",\"\",\"Async\"]}");
    }
}
