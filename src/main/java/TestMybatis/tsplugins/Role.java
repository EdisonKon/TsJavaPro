package TestMybatis.tsplugins;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-08 21:55
 * @from 用于测试自定义插件
 **/
@Data
public class Role {
    private long id;
    @Value("role_name")
    private String roleName;
    private JSONObject note = new JSONObject();
    private String password;
}
