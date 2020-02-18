package TsWebSocket.objs;

import TsWebSocket.TsWebSocket;
import org.junit.Test;

import java.util.List;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-11 15:11
 * @from
 * 发送对象
 **/
public class RequestObj {
    public RequestObj() {

    }

    private String method;

    private String object;

    private List<Object> params;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    @Test
    public void test() {

    }
}
