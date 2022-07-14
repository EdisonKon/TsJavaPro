package TestMybatis.tsplugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-08 22:48
 * @from  改变出参集合
 **/

//@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = {MappedStatement.class})})
//@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})})
//@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
//@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,Object.class})
})
public class MyPlugin4Result implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println(invocation);
        DefaultResultSetHandler target = (DefaultResultSetHandler) invocation.getTarget();
//        target.handleResultSets()
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
