package TsWebSocket.enums;


public enum TurnEnum {

    HEARTBEAT(-1, "heartbeat","cti"),
    ERROR_CLOSE(0, "close","cti"),
    RECONNECTSESSION(-2, "reconnectSession","cti"),

    //TODO 顺序
    INITSESSION(1, "initSession","cti"),
    QUERYDICTIONARY(2, "queryDictionary","res"),
    SNAPSHOTDEVICE_DEVICEID(3, "snapshotDevice","cti"),
    SNAPSHOTDEVICE_AGENTID(4, "snapshotDevice","cti"),
    SETAGENTSTATE(5, "setAgentState","cti"),

    MONITORDEVICE1(6, "monitorDevice","cti"),
    INITDESKTOP(7, "initDesktop","cti"),
    SETAGENTAUTOWORKMODE(8, "setAgentAutoWorkMode","cti"),
    SNAPSHOTDEVICE_DEVICEID2(9, "snapshotDevice","cti"),
//    MONITORDEVICE2(10, "monitorDevice","cti"),






;


    TurnEnum(int id, String desc,String type) {
        this.id = id;
        this.desc = desc;
        this.type = type;
    }

    private int id;
    private String desc;
    private String type;

    public static TurnEnum getFunById(int id){
        for(TurnEnum consoleTypeEnum : values()){
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

    public String getType(){return type;}
}
