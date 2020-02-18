package TsWebSocket.objs;

import com.alibaba.fastjson.annotation.JSONField;
import org.junit.Test;

import java.util.Map;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-11 17;
 * @from
 *
 * 参数对象
 **/
public class RequestParamObj {

    private String deviceId;
    private String agentId;
    private String password;
    private String agentMode;
    private Object func;
    private String group;
    private String reason;
    private Boolean syncAtOnce;
    private String autoWorkMode;
    private String dest;
    private String origin;
    private String activeCall;
    @JSONField(serialize=false)
    private String uui ;
    private String transferredTo;
    private Object userData;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgentMode() {
        return agentMode;
    }

    public void setAgentMode(String agentMode) {
        this.agentMode = agentMode;
    }

    public Object getFunc() {
        return func;
    }

    public void setFunc(Object func) {
        this.func = func;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getSyncAtOnce() {
        return syncAtOnce;
    }

    public void setSyncAtOnce(Boolean syncAtOnce) {
        this.syncAtOnce = syncAtOnce;
    }

    public String getAutoWorkMode() {
        return autoWorkMode;
    }

    public void setAutoWorkMode(String autoWorkMode) {
        this.autoWorkMode = autoWorkMode;
    }

    public String getActiveCall() {
        return activeCall;
    }

    public void setActiveCall(String activeCall) {
        this.activeCall = activeCall;
    }

    public String getTransferredTo() {
        return transferredTo;
    }

    public void setTransferredTo(String transferredTo) {
        this.transferredTo = transferredTo;
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUui() {
        return deviceId+",fwrv,ervdsdfv,vservb";
    }

    public void setUui(String uui) {
        this.uui = uui;
    }

    public RequestParamObj() {

    }


    @Test
    public void test() {

    }
}
