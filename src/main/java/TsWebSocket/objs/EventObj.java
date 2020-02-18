package TsWebSocket.objs;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-13 11:15
 * @from
 * 时间对象
 **/
public class EventObj {
    private String name;
    private String source;
    private String timestamp;
    private EventObjProp properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public EventObjProp getProperties() {
        return properties;
    }

    public void setProperties(EventObjProp properties) {
        this.properties = properties;
    }
}
