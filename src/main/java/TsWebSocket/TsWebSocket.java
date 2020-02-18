package TsWebSocket;

import TsWebSocket.WsPackage.WsClient;
import TsWebSocket.WsPackage.WsFunc;
import TsWebSocket.objs.BackObj;
import org.java_websocket.WebSocket;
import org.junit.Test;

import java.net.URI;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-11 14:24
 * @from
 **/
public class TsWebSocket {

    public TsWebSocket() {

    }

    // 根据实际websocket地址更改
    private static String url = "ws://10.101.101.77:8123/websocket";
    private static String backurl = "ws://10.101.101.78:8123/websocket";



    static WsClient myClient;




    @Test
    public void test() {
    }

    public static void main(String[] args){
        boolean isConn = true;
        try {
            int i = 0;
            myClient = new WsClient(new URI(url));
            WsFunc wsFunc = new WsFunc(myClient);
            myClient.setWsHandle(wsFunc);
            myClient.connect();

            // 判断是否连接成功，未成功后面发送消息时会报错
            while (!myClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                i++;
                if(i==5){
                    myClient = new WsClient(new URI(backurl));
                    myClient.connect();
                }
                System.out.println("连接中···请稍后");
                Thread.sleep(1000);
            }
            BackObj backObj = new BackObj();
            wsFunc.judgeBackObj(backObj);
            while(isConn){
                Thread.sleep(10000);
                wsFunc.genAndSendHbMsg(myClient.turn.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
