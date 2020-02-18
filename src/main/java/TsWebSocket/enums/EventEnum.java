package TsWebSocket.enums;


public enum EventEnum {

    /**
     * 初始化
     */
    serviceInitiated(1, "serviceInitiated","cti",""),
    /**
     * 发起
     */
    originated(2, "originated","cti",""),
    /**
     * 递交
     */
    delivered(3, "delivered","cti",""),
    /**
     * 建立
     */
    established(4, "established","cti",""),
    /**
     * 生成会议
     */
    conferenced(5, "conferenced","cti",""),
    /**
     * 保持
     */
    held(6, "held","cti",""),
    /**
     * 失败
     */
    failed(7, "failed","cti",""),
    /**
     * 链接清除
     */
    connectionCleared(8, "connectionCleared","cti",""),
    /**
     * 恢复
     */
    retrieved(9, "retrieved","cti",""),
    /**
     * 代理登入
     */
    agentLoggedOn(10, "agentLoggedOn","cti",""),
    /**
     * 代理登出
     */
    agentLoggedOff(11, "agentLoggedOff","cti",""),
    /**
     * 快照
     */
    snapshot(12, "snapshot","cti",""),
    /**
     * 网络到达
     */
    networkReached(13, "networkReached","cti",""),


;


    EventEnum(int id, String desc, String type, String funx) {
        this.id = id;
        this.desc = desc;
        this.type = type;
        this.funx = funx;
    }

    private int id;
    private String desc;
    private String type;
    private String funx;

    public static EventEnum getFunById(int id){
        for(EventEnum consoleTypeEnum : values()){
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
