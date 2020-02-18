package TsWebSocket;

import TsWebSocket.WsPackage.WsClient;
import TsWebSocket.enums.OperationEnum;
import TsWebSocket.enums.TurnEnum;
import TsWebSocket.objs.RequestObj;
import TsWebSocket.objs.RequestParamObj;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-11 14:58
 * @from
 * //有几个步骤
 * 1.initSession
 * 2.queryDictionary
 * 3.snapshotDevice/snapshotDevice 2次因为坐席号/工号2次
 * 4.setAgentState
 * 5.
 **/
public class WsConfig {

    private static String agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36;";

    private static String start = "CT4001";

    private static String deviceId = "950976";
    private static String deviceId2 = "950975";

    private static String agentId = "963822";

    private static String pwd = "000000";

    private static String dest = "0013602140154";

    private WsClient wsClient;

    public WsConfig() {

    }

    public WsConfig(WsClient wsClient) {
        this.wsClient = wsClient;
    }

    /**
     * 获取拼接字符串
     * @param turn
     * @param fun
     * @return
     */
    public String getFinalStr(int turn, TurnEnum fun){
        String end = getNextStr(fun);
        return concatFinalStr(end,turn);
    }

    /**
     * 获取拼接字符串
     * @param turn
     * @param opfun
     * @return
     */
    public String getFinalStr4Op(int turn, OperationEnum opfun){
        String end = getNextStr4Op(opfun);
        return concatFinalStr(end,turn);
    }

    /**
     * 拼接字符串
     * @param end
     * @param turn
     * @return
     */
    public static String concatFinalStr(String end,int turn){
        int len = end.length();
        String paramLen = Utils.getZeroStr(len+"");
        String turnLen = Utils.getZeroStr(turn+"");
        String curStr = start+paramLen+turnLen;
        String finalStr = Utils.getSpaceStr(curStr)+end;
        return finalStr;
    }

    /**
     * 获取param字符串
     * @param fun
     * @return
     */
    public String getNextStr(TurnEnum fun){
        String val ;
        List<Object> list ;
        RequestObj requestObj = new RequestObj();
        list = switchFun(fun);
        requestObj.setMethod(fun.getDesc());
        requestObj.setObject(fun.getType());

        requestObj.setParams(list);

        val = JSON.toJSONString(requestObj);
        return val;

    }

    /**
     * 获取param字符串
     * @param opfun
     * @return
     */
    public String getNextStr4Op(OperationEnum opfun){
        String val ;
        List<Object> list ;
        RequestObj requestObj = new RequestObj();
        list = switchFun4Op(opfun);
        requestObj.setMethod(opfun.getDesc());
        requestObj.setObject(opfun.getType());

        requestObj.setParams(list);

        val = JSON.toJSONString(requestObj);
        return val;

    }

    /**
     * 获取日期格式
     * @return
     */
    public String getDateStr(){
        Calendar cd = Calendar.getInstance();
        //Tue, 11 Feb 2020 06:49:33 GMT
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        String str = sdf.format(cd.getTime());
        System.out.println(str);
        return str;
    }


    /**
     * 转换接下来的方法,放入参数
     * @param fun
     * @return
     */
    public List<Object> switchFun(TurnEnum fun){
        List<Object> list = new ArrayList<>();
        RequestParamObj requestParamObj;
        if(fun==null){
            return list;
        }
        switch (fun){
            case ERROR_CLOSE:
            case HEARTBEAT:
                break;
            case INITSESSION:
                list.add("");
                list.add(agent+getDateStr());
                list.add("");
                list.add("Async");
                break;
            case RECONNECTSESSION:
                list.add(wsClient.getSessionId());
                list.add("");
                break;
            case QUERYDICTIONARY:
                list.add("REASON_CODE");
                break;
            case SNAPSHOTDEVICE_DEVICEID:
            case SNAPSHOTDEVICE_DEVICEID2:
                requestParamObj = new RequestParamObj();
                requestParamObj.setDeviceId(deviceId);
                requestParamObj.setFunc(1);
                requestParamObj.setSyncAtOnce(false);
                list.add(requestParamObj);
                break;
            case SNAPSHOTDEVICE_AGENTID:
                requestParamObj = new RequestParamObj();
                requestParamObj.setAgentId(agentId);
                requestParamObj.setFunc(1);
                requestParamObj.setSyncAtOnce(false);
                list.add(requestParamObj);
                break;
            case SETAGENTSTATE:
                requestParamObj = new RequestParamObj();
                requestParamObj.setDeviceId(deviceId);
                requestParamObj.setAgentId(agentId);
                requestParamObj.setPassword(pwd);
                requestParamObj.setAgentMode("NotReady");
                requestParamObj.setFunc("Login");
                requestParamObj.setGroup("");
                requestParamObj.setReason("0");
                list.add(requestParamObj);
                break;
            case MONITORDEVICE1:
                list.add(deviceId);
                break;
            case INITDESKTOP:
                list.add(agentId);
                list.add(deviceId);
                break;
            case SETAGENTAUTOWORKMODE:
                requestParamObj = new RequestParamObj();
                requestParamObj.setAutoWorkMode("ManualIn");
                requestParamObj.setAgentId(agentId);
                list.add(requestParamObj);
                break;
        }

        return list;
    }

    /**
     * 转换接下来的方法,放入参数
     * @param fun
     * @return
     */
    public List<Object> switchFun4Op(OperationEnum fun){
        List<Object> list = new ArrayList<>();
        RequestParamObj requestParamObj = new RequestParamObj();
        if(fun==null){
            return list;
        }
        switch (fun){
            case MAKECALL:
                requestParamObj.setDest(dest);
                requestParamObj.setDeviceId(deviceId);
                requestParamObj.setOrigin(deviceId);
                list.add(requestParamObj);
                break;
            case CLEARCONNECTION:
                list.add("");
                list.add(agent+getDateStr());
                list.add("");
                list.add("Async");
                break;
            case SETAGENTSTATE_LOGIN:
                requestParamObj.setAgentMode("NotReady");
            case SETAGENTSTATE_LOGOUT:
                requestParamObj.setDeviceId(deviceId);
                requestParamObj.setAgentId(agentId);
                requestParamObj.setPassword(pwd);
                requestParamObj.setAgentMode("Logout");
                requestParamObj.setFunc(fun.getFunx());
                requestParamObj.setGroup("");
                requestParamObj.setReason("0");
                list.add(requestParamObj);
                break;
            case STOPMONITORDEVICE:
                list.add(deviceId);
                break;
            case SINGLESTEPTRANSFERCALL:
                requestParamObj.setActiveCall(wsClient.getCallId());
                requestParamObj.setDeviceId(deviceId);
                requestParamObj.setTransferredTo(deviceId2);
                Map map = new HashMap<>();
                Map mapM = new HashMap<>();
                map.put("uui",requestParamObj.getUui());
                mapM.put("map",map);
                requestParamObj.setUserData(mapM);
                list.add(requestParamObj);
                break;
        }

        return list;
    }


    public static void main(String[] args) {
        WsConfig wsc = new WsConfig();
        wsc.switchFun4Op(OperationEnum.SINGLESTEPTRANSFERCALL);

//        CT40010000015700000015                                          {"method":"singleStepTransferCall","object":"cti","params":[{"activeCall":"10179","deviceId":"950976","transferredTo":"950975","userData":{"uui":"950976,fwrv,ervdsdfv,vservb"}}]}
//        CT40010000018600000282                                          {"method":"singleStepTransferCall","object":"cti","params":[{"activeCall":"10179","deviceId":"950976","transferredTo":"950975","userData":{"map":{"uui":"950974,fwrv,ervdsdfv,vservb"}}}]}
//        CT40010000017800000015                                          {"method":"singleStepTransferCall","object":"cti","params":[{"activeCall":"13444","deviceId":"950976","transferredTo":"950975","userData":{"uui":"950976,fwrv,ervdsdfv,vservb"}}]}

    }
}
