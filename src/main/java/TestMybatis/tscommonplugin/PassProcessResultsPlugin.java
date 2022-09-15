package TestMybatis.tscommonplugin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-09-09 17:48
 * @from  改变出参集合
 **/

@Component
@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class PassProcessResultsPlugin implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(PassProcessResultsPlugin.class);
    private List<String> keys = new ArrayList<>();
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        DefaultResultSetHandler target = (DefaultResultSetHandler) invocation.getTarget();
        List<Object> objects1 = target.handleResultSets((Statement) invocation.getArgs()[0]);
        if(objects1.size()>0){
            //重新加载所有key对应方法
            keys.clear();
            keys.addAll(KeyMethodMapping.keyInMethod.keySet());
            Object obj = objects1.get(0);
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = FieldUtils.getAllFields(aClass);
            for(Object pojo:objects1){
                for (Field f : declaredFields) {
                    List<String> collect = keys.stream().filter(xkey -> f.getName().toLowerCase().contains(xkey.toLowerCase())).collect(Collectors.toList());
                    if (collect.size()>0) {
                        f.setAccessible(true);
                        Object origVal = f.get(pojo);
                        try {
                            Method method = KeyMethodMapping.keyOutMethod.get(collect.get(0));
                            Object o = method.getDeclaringClass().newInstance();
                            String finalVal = (String) method.invoke(o ,origVal);
                            f.set(pojo, finalVal);
                        }catch (Exception e){
                            logger.info("解密处理失败");
                        }
                    }else if(f.getType().equals(JSONObject.class)
                            || f.getType().equals(JSONArray.class)){
                        f.setAccessible(true);
                        Object origVal = f.get(pojo);
                        try {
                            AnalysisUtil.analysisJson(origVal, KeyMethodMapping.keyOutMethod, keys);
                        }catch (Exception e){
                            logger.info("解密处理失败");
                        }
                    }
                }
            }
            return objects1;
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
