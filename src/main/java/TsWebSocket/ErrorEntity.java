package TsWebSocket;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-14 11:03
 * @from
 **/
public class ErrorEntity {
    private String INVOKE_TIMEOUT = "请求超时";
    private String SOCKET_NOT_OPEN = "连接未建立";
    private String SESSION_NOT_ALIVE = "会话未建立";
    private String STATION_NOT_MONITORED = "分机未监视";
    private String DCMP_CONNECTION_FAILED = "地址连接失败";
    private String SERVER_CONNECTION_FAILED = "服务器连接失败";
    private String NO_AVAILABLE_SERVER = "没有可用的服务器";
    private String INVALID_STATE_ERR = "无效的状态";
    private String NO_DEVICE_INFO = "未获取到分机信息";
    private String EXIST_AGENT_LOGIN = "工号已登录";
    private String EXIST_DEVICE_LOGIN = "分机已登录";
    private String NOT_LOGGED_IN = "未登录";
    private String INVALID_DEVICE_ID = "无效的分机号";
    private String INVALID_AGENT_ID = "无效的工号";
    private String INVALID_PASSWORD = "无效的密码";
    private String UNKNOW_ERROR = "未知错误";
    private String Error = "其他错误";
    private String GENERIC_UNSPECIFIED = "坐席已登录(登录)或未登录(退出)";
    private String GENERIC_OPERATION = "分机已被登录,或者分机号码未被注册(ONE-X 或实体话机）";
    private String REQUEST_INCOMPATIBLE_WITH_OBJECT = "监控的设备在交换机中存在异常,可能原因,ACD License 过期";
    private String VALUE_OUT_OF_RANGE = "值超过范围";
    private String OBJECT_NOT_KNOWN = "在单步会议中 callId参数没有包含当前呼叫";
    private String INVALID_CALLING_DEVICE = "无效的主叫号码";
    private String INVALID_CALLED_DEVICE = "无效的被叫号码";
    private String INVALID_CSTA_CALL_IDENTIFIER = "无效的呼叫标识";
    private String INVALID_OBJECT_STATE = "无效的对象状态";
    private String GENERIC_STATE_INCOMPATIBILITY = "状态不兼容";
    private String INVALID_CONNECTION_ID_FOR_ACTIVE_CALL = "分机号在活跃呼叫和保持呼叫中的标记不一致";
    private String NO_ACTIVE_CALL = "当前没有可用的呼叫";
    private String NO_HELD_CALL = "当前没有保持的呼叫";
    private String RESOURCE_BUSY = "资源忙,无效的分机状态,比如登录时分机可能正在通话";
    private String CONFERENCE_MEMBER_LIMIT_EXCEEDED = "会议成员超过限制";


    public static Map<String,String> getErrMap(){
        Map<String,String> mKv = new HashMap<>();
        ErrorEntity errorEntity = new ErrorEntity();
        Field[] fields = ErrorEntity.class.getDeclaredFields();
        for (Field f : fields) {
            try {
                mKv.put(f.getName(), (String) f.get(errorEntity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mKv;
    }

    public static void main(String[] args) {
        Map<String,String> mKv = getErrMap();
        System.out.println(mKv);
    }

}
