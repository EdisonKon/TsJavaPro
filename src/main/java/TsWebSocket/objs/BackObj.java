package TsWebSocket.objs;

import TsWebSocket.TsWebSocket;
import com.alibaba.fastjson.annotation.JSONField;
import org.junit.Test;


/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-11 16:18
 * @from
 *
 * 返回对象
 **/
public class BackObj {
    public BackObj() {

    }

    private String method;
    private Integer invokeId;
    private String object;
    private Object ret;
    private String errMessage;
    @JSONField (serialize=false)
    private boolean init;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(Integer invokeId) {
        this.invokeId = invokeId;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Object getRet() {
        return ret;
    }

    public void setRet(Object ret) {
        this.ret = ret;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public boolean getInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    @Test
    public void test() {

    }

}
