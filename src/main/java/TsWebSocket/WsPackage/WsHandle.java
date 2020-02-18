package TsWebSocket.WsPackage;

import TsWebSocket.Utils;
import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-12 12:59
 * @from
 **/
public class WsHandle {

    private static String start = "CT4099";


    private WsFunc wsFunc;

    public WsHandle(WsFunc wsFunc) {
        this.wsFunc = wsFunc;
    }


    public void onMessageHandel(String msg){
        //TODO 如果是4099通知类型的开头,那么怎么处理事件
        if(msg.startsWith(start)){
            System.out.println("收到事件:"+msg);
            wsFunc.judgeEvent(Utils.getContent(msg));
            return;
        }
        wsFunc.judgeBackObj(Utils.getContent(msg));
    }

    public void onCloseHandel(){

        System.out.println("非正常关闭");
        System.exit(-1);
    }
}
