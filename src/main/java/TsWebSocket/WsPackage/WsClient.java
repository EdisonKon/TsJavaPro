package TsWebSocket.WsPackage;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-11 14:25
 * @from
 **/
public class WsClient extends WebSocketClient {

    public AtomicInteger turn = new AtomicInteger(0);
    private boolean init = true;
    /**
     * sessionId
     */
    private String sessionId;
    /**
     * 打电话的id
     */
    private String callId;

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public WsClient(URI serverUri) {
        super(serverUri);
    }

    public WsHandle wsHandle;

    public void setWsHandle(WsFunc wsFunc){
        this.wsHandle = new WsHandle(wsFunc);
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        System.out.println("握手成功");

    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println(arg0);
        System.out.println(arg1);
        System.out.println(arg2);
        System.out.println("连接关闭");
        wsHandle.onCloseHandel();
    }

    @Override
    public void onError(Exception arg0) {
        System.out.println(arg0);
        System.out.println("发生错误");
    }

    @Override
    public void onMessage(String arg0) {
        System.out.println("收到消息:\n" + arg0);
        wsHandle.onMessageHandel(arg0);
    }


}
