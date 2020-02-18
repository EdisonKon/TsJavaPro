package TsWebSocket.WsPackage;

import TsWebSocket.objs.BackObj;
import TsWebSocket.WsConfig;
import TsWebSocket.enums.OperationEnum;
import TsWebSocket.enums.TurnEnum;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-12 12:58
 * @from
 **/
public class WsFunc {

    public WsClient client;

    public WsFunc(WsClient client) {
        this.client = client;
    }


    /**
     * 判断事件,并处理
     * @param backObj
     * @return
     */
    public void judgeEvent(BackObj backObj) {
        //TODO 事件的处理 4099

    }
    /**
     * 判断返回的消息属于什么,并处理
     * @param backObj
     * @return
     */
    public void judgeBackObj(BackObj backObj) {
        client.turn.incrementAndGet();
        //TODO 测试打电话
        if(client.turn.get()==11){
            String str = generateMsgMethod4Op(1);
            sendMsg(str);
        }

        if(client.turn.get()==15){
            String str = generateMsgMethod4Op(5);
            sendMsg(str);
        }

        if(client.turn.get()==20){
            String str = generateMsgMethod4Op(4);
            sendMsg(str);
        }
        if(TurnEnum.HEARTBEAT.getDesc().equals(backObj.getMethod())){
            System.out.println("心跳接收");
            return;
        }

        //TODO 当初始化时候,按照顺序发送socket
        if(client.isInit()){
            genAndSendMsg(backObj);
        }else{
            ErrMsgHandle handle = new ErrMsgHandle();
            //TODO 当非初始化时候 暂且不发socket
            //TODO 只处理的打电话出去的返回事件
            if(OperationEnum.MAKECALL.getDesc().equals(backObj.getMethod())){
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(backObj.getRet());
                client.setCallId(jsonObject.getString("callId"));
            }else if(OperationEnum.SINGLESTEPTRANSFERCALL.getDesc().equals(backObj.getMethod())){
                sendMsg(handle.singleErrHandle(backObj));
            }
        }
    }

    /**
     * 根据返回对象生成顺序ws信息
     * @param backObj
     */
    public void genAndSendMsg(BackObj backObj){
        String initStr;
        if(backObj.getErrMessage()==null){
            initStr = generateMsgMethod();
        }else{
            initStr = errMsghandle(backObj);
        }
        if("".equals(initStr)){
            return;
        }
        sendMsg(initStr);
    }

    /**
     * 通用错误处理 顺序请求时候的处理
     */
    public String errMsghandle(BackObj backObj){
        String initStr;
        if(backObj.getErrMessage().contains("GENERIC_OPERATION")){
            initStr = generateMsgMethod(true);
        }else{
            System.out.println(backObj.getErrMessage());
            /**
             * 如果产生错误,那么可以更换账号等等操作去处理
             * 此处直接退出
             * TODO
             */
            initStr = generateMsgMethod();
        }
        return initStr;
    }


    /**
     * 生成发送的消息
     * @param isError
     * @return
     */
    private String generateMsgMethod(boolean... isError) {
        FuncSets funcSets = new FuncSets(client);
        String finalStr;
        if(isError.length>0 && isError[0]){
            finalStr = funcSets.errorClose(client.turn.get());
        }else{
            TurnEnum fun = TurnEnum.getFunById(client.turn.get());
            if(fun == null){
                client.setInit(false);
                return "";
            }
            if(client.isInit()){
                WsConfig wsc = new WsConfig(client);
                finalStr = wsc.getFinalStr(client.turn.get(),fun);
            }else{
                //TODO 非初始化,进行其他处理
                finalStr = "";
            }
        }

        return finalStr;
    }

    /**
     * 生成发送的消息
     * @return
     */
    private String generateMsgMethod4Op(int opTrun) {
        String finalStr = "";
        FuncSets funcSets = new FuncSets(client);
        try {
            String mname = OperationEnum.getFunById(opTrun).getDesc();
            Method method = FuncSets.class.getMethod(mname, int.class,int.class);
            finalStr = (String) method.invoke(funcSets,client.turn.get(),opTrun);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return finalStr;
    }

    /**
     * 发送心跳
     * @param turn
     */
    public void genAndSendHbMsg(int turn){
        FuncSets funcSets = new FuncSets(client);
        String finalStr = funcSets.heartBeat(turn);
        sendMsg(finalStr);
    }


    public void sendMsg(String msg){
        if("".equals(msg)){
            return;
        }
        System.out.println("发送消息:\n"+msg);
        client.send(msg);
    }
}
