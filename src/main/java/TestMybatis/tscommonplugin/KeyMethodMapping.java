package TestMybatis.tscommonplugin;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Data
public class KeyMethodMapping {
    public static Map<String, Method> keyInMethod = new HashMap<>(16);
    public static Map<String, Method> keyOutMethod = new HashMap<>(16);

}
