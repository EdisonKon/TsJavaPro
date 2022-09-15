package TestMybatis.tsplugins;

import TestMybatis.JsonTypeHandler;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.val;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-08 22:48
 * @from  改变出参集合
 **/

//@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = {MappedStatement.class})})
//@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})})
//@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
//@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,Object.class})
})
public class MyPlugin4Result implements Interceptor {

    private List<String> keys = Arrays.asList("password","aaa");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        DefaultResultSetHandler target = (DefaultResultSetHandler) invocation.getTarget();
        List<Object> objects1 = target.handleResultSets((Statement) invocation.getArgs()[0]);
        if(objects1.size()>0){
            Object obj = objects1.get(0);
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = FieldUtils.getAllFields(aClass);
            for(Object pojo:objects1){
                for (Field f : declaredFields) {
                    List<String> collect = keys.stream().filter(xkey -> f.getName().toLowerCase().contains(xkey)).collect(Collectors.toList());
                    if (collect.size()>0) {
                        f.setAccessible(true);
                        Object origVal = f.get(pojo);
                        System.out.println("原始值："+origVal);
                        f.set(pojo,AnalysisUtil.decryptStr((String)origVal));
                    }else if(f.getType().equals(JSONObject.class)
                            || f.getType().equals(JSONArray.class)){
                        f.setAccessible(true);
                        Object origVal = f.get(pojo);
                        AnalysisUtil.analysisJson(origVal,AnalysisUtil.class.getMethod("decryptStr",String.class),keys);
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
