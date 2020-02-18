package TsWebSocket.WsPackage;


import TsWebSocket.WsConfig;
import TsWebSocket.enums.OperationEnum;
import TsWebSocket.enums.TurnEnum;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-12 12:41
 * @from
 **/
public class FuncSets {

    public WsClient client;

    public FuncSets(WsClient client) {
        this.client = client;
    }
    
    public String errorClose(int turn){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr(turn,TurnEnum.ERROR_CLOSE);
    }

    public String heartBeat(int turn){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr(turn,TurnEnum.HEARTBEAT);
    }
    public String initSession(int turn){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr(turn,TurnEnum.INITSESSION);
    }
    public String queryDictionary(int turn){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr(turn,TurnEnum.QUERYDICTIONARY);
    }
    public String snapshotDevice(int turn){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr(turn,TurnEnum.SNAPSHOTDEVICE_DEVICEID);
    }

    public String monitorDevice(int turn){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr(turn,TurnEnum.MONITORDEVICE1);
    }
    public String initDesktop(int turn){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr(turn,TurnEnum.INITDESKTOP);
    }
    public String setAgentAutoWorkMode(int turn){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr(turn,TurnEnum.SETAGENTAUTOWORKMODE);
    }

    /**
     * 手动
     * @param turn
     * @return
     */
    public String makeCall(int turn,int which){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr4Op(turn,OperationEnum.MAKECALL);
    }
    public String clearConnection(int turn,int which){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr4Op(turn,OperationEnum.CLEARCONNECTION);
    }
    public String setAgentState(int turn,int which){
        WsConfig wsc = new WsConfig(client);
        if (which == 3) {
            return wsc.getFinalStr4Op(turn, OperationEnum.SETAGENTSTATE_LOGIN);
        }else{
            return wsc.getFinalStr4Op(turn, OperationEnum.SETAGENTSTATE_LOGOUT);
        }
    }


    public String stopMonitorDevice(int turn,int which){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr4Op(turn, OperationEnum.STOPMONITORDEVICE);
    }
    public String singleStepTransferCall(int turn,int which){
        WsConfig wsc = new WsConfig(client);
        return wsc.getFinalStr4Op(turn, OperationEnum.SINGLESTEPTRANSFERCALL);
    }

}
