package TestMybatis.tscommonplugin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


@Component
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}),
})
public class PassProcessParamsPlugin implements Interceptor {
    private List<String> keys = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(PassProcessParamsPlugin.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 参数代理
        if (invocation.getTarget() instanceof ParameterHandler) {
            System.out.println("ParameterHandler");
            // 自动处理password的加密信息
            try{
                autoProcessPassword(invocation);
            }catch (Exception e){
                logger.info("加密处理失败");
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 自动处理password的加密信息
     *
     * @param invocation 代理对象
     * @throws Throwable 异常
     */
    private void autoProcessPassword(Invocation invocation) throws Throwable {
        // 获取代理的参数对象ParameterHandler
        ParameterHandler ph = (ParameterHandler) invocation.getTarget();
        // 通过MetaObject获取ParameterHandler的反射内容
        MetaObject metaObject = MetaObject.forObject(ph,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory());
        // 通过MetaObject反射的内容获取MappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("mappedStatement");
        // 当sql类型为INSERT或UPDATE时，自动处理password的加密信息
        if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT ||
                mappedStatement.getSqlCommandType() == SqlCommandType.UPDATE) {
            //重新加载所有key对应方法
            keys.clear();
            keys.addAll(KeyMethodMapping.keyInMethod.keySet());
            // 获取参数对象
            Object pojo = ph.getParameterObject();
            if (null != pojo) {
                // 通过反射获取参数对象的属性
                Class<?> aClass = pojo.getClass();
                Field[] fields = FieldUtils.getAllFields(aClass);
                // 遍历参数对象的属性
                for (Field f : fields) {
                    // 如果存在password/其他属性
                    List<String> collect = keys.stream().filter(xkey -> f.getName().toLowerCase().contains(xkey.toLowerCase())).collect(Collectors.toList());
                    if (collect.size()>0) {
                        // 设置允许访问反射属性
                        f.setAccessible(true);
                        Object origVal = f.get(pojo);
                        if (null != origVal) {
                            Method method = KeyMethodMapping.keyInMethod.get(collect.get(0));
                            Object o = method.getDeclaringClass().newInstance();
                            String finalVal = (String) method.invoke(o ,origVal);
                            f.set(pojo, finalVal);
                        }
                    }else if(f.getType().equals(JSONObject.class)
                            || f.getType().equals(JSONArray.class)){
                        f.setAccessible(true);
                        Object origVal = f.get(pojo);
                        AnalysisUtil.analysisJson(origVal,KeyMethodMapping.keyInMethod,keys);
                    }
                }

                // 通过反射获取ParameterHandler的parameterObject属性
                Field parameterObject = ph.getClass().getDeclaredField("parameterObject");
                // 设置允许访问parameterObject属性
                parameterObject.setAccessible(true);
                // 将上面设置的新参数对象设置到ParameterHandler的parameterObject属性
                parameterObject.set(ph, pojo);
            }
        }
    }
}
