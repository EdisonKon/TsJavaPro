package TestMybatis.tscommonplugin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalysisUtil {

    /**
     * 递归遍历JSON对象。
     * @param objJson
     * @param keyMethod 可以传入不同的方法去处理值
     */
    public static void  analysisJson(Object objJson, Map<String,Method> keyMethod, List<String> keys) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        //如果obj为json数组
        if(objJson instanceof JSONArray){
            JSONArray objArray = (JSONArray)objJson;
            for (int i = 0; i < objArray.size(); i++) {
                analysisJson(objArray.get(i),keyMethod,keys);
            }
        }
        //如果为json对象
        else if(objJson instanceof JSONObject){
            JSONObject jsonObject = (JSONObject)objJson;
            Iterator it = jsonObject.keySet().iterator();
            while(it.hasNext()){
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if(object instanceof JSONArray){
                    JSONArray objArray = (JSONArray)object;
                    analysisJson(objArray,keyMethod,keys);
                }
                //如果key中是一个json对象
                else if(object instanceof JSONObject){
                    analysisJson(object,keyMethod,keys);
                }
                //如果key中是普通值
                else{
                    // 便利key是否包含提供的要执行替换方法的keys 例如 keys里有password和user都需要加密，那么便利key是否为password或者user
                    List<String> collect = keys.stream().filter(xkey -> key.toLowerCase().contains(xkey.toLowerCase())).collect(Collectors.toList());
                    // 知道大于0则证明有需要替换的key，直接执行method方法就行，不用遍历
                    if(collect.size()>0){
                        Method method = keyMethod.get(collect.get(0));
                        Object o = method.getDeclaringClass().newInstance();
                        String finalVal = (String) method.invoke(o,jsonObject.get(key));
                        jsonObject.put(key,finalVal);
                    }
                }
            }
        }
    }

    /**
     * 加密
     * @param origVal
     * @return
     */
    public static String encryptStr(String origVal){
        return "加密:"+origVal;
    }

    /**
     * 解密
     * @param origVal
     * @return
     */
    public static String decryptStr(String origVal){
        return "解密:"+ origVal;
    }

}
