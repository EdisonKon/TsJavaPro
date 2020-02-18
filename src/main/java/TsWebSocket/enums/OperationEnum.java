package TsWebSocket.enums;


public enum OperationEnum {

    /**
     * 打电话
     */
    MAKECALL(1, "makeCall","cti",""),
    /**
     * 挂断
     */
    CLEARCONNECTION(2, "clearConnection","cti",""),
    /**
     * 登入
     */
    SETAGENTSTATE_LOGIN(3, "setAgentState","cti","Login"),
    /**
     * 登出
     */
    SETAGENTSTATE_LOGOUT(4, "setAgentState","cti","Logout"),
    /**
     * 停止监控
     */
    STOPMONITORDEVICE(41, "stopMonitorDevice","cti","cti"),
    /**
     * 单转
     */
    SINGLESTEPTRANSFERCALL(5, "singleStepTransferCall","cti","cti"),




;


    OperationEnum(int id, String desc, String type,String funx) {
        this.id = id;
        this.desc = desc;
        this.type = type;
        this.funx = funx;
    }

    private int id;
    private String desc;
    private String type;
    private String funx;

    public static OperationEnum getFunById(int id){
        for(OperationEnum consoleTypeEnum : values()){
            if(consoleTypeEnum.getId() == id){
                return consoleTypeEnum;
            }
        }
        return null;
    }
    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getFunx(){return funx;}

    public String getType(){return type;}
}
