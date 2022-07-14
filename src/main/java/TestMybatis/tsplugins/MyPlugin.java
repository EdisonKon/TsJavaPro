package TestMybatis.tsplugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-08 22:48
 * @from  只能获取sql, 和参数 无法改变出参
 **/

//@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = {MappedStatement.class})})
//@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})})
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Map<String,String> mapMapping = new HashMap<>(16);
        List<ResultMapping> keyMapping = new ArrayList<>();
        System.out.println(invocation);
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        MappedStatement mp = (MappedStatement) metaObject.getValue("delegate.resultSetHandler.mappedStatement");
        List resultMaps = mp.getResultMaps();
        Configuration config = (Configuration) metaObject.getValue("delegate.configuration");
        final TypeHandlerRegistry registry = config.getTypeHandlerRegistry();

        ResultMap rm = null;
        if(resultMaps!=null){
            rm = (ResultMap) resultMaps.get(0);
            Class<?> type = rm.getType();
            Field[] declaredFields = type.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                if(declaredFields[i].isAnnotationPresent(Value.class)){
                    String key = declaredFields[i].getName();
                    String val = declaredFields[i].getAnnotation(Value.class).value();
                    mapMapping.put(key,val);
                    keyMapping.add(new ResultMapping.Builder(config, key, val, registry.getTypeHandler(declaredFields[i].getType())).build());
                }
            }
        }
        if(rm == null){
            return invocation.proceed();
        }
        System.out.println(mapMapping);
        BoundSql boundSql = handler.getBoundSql();
        handler.getParameterHandler().getParameterObject();
        String sql = boundSql.getSql();
        for(Map.Entry<String,String> entry: mapMapping.entrySet()){
            sql = sql.replace(entry.getKey(),entry.getValue());
        }


        System.out.println(sql);
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, sql);

        List<ResultMapping> resultMappings = new ArrayList<>();
        resultMappings.addAll(rm.getResultMappings());
        resultMappings.addAll(keyMapping);

        List<ResultMap> resultMapsx = new ArrayList<ResultMap>();
        ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(
                config,
                rm.getId(),
                rm.getType(),
                resultMappings,
                null);

        Field fieldmap = mp.getClass().getDeclaredField("resultMaps");
        fieldmap.setAccessible(true);
        resultMapsx.add(inlineResultMapBuilder.build());
        fieldmap.set(mp, resultMapsx);


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
