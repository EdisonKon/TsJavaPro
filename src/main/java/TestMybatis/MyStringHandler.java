package TestMybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-08 22:15
 * @from
 **/
@MappedTypes({String.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyStringHandler implements TypeHandler<String> {

    @Override
    public String getResult(ResultSet rs, String colName) throws SQLException {
        System.out.println("使用我的TypeHandler,ResultSet列名获取字符串");
        return rs.getString(colName);
    }

    @Override
    public String getResult(ResultSet rs, int index) throws SQLException {
        System.out.println("使用我的TypeHandler,ResultSet下标获取字符串");
        return rs.getString(index);
    }

    @Override
    public String getResult(CallableStatement cs, int index) throws SQLException {
        System.out.println("使用我的TypeHandler,CallableStatement下标获取字符串");
        return cs.getString(index);
    }

    @Override
    public void setParameter(PreparedStatement ps, int index, String value, JdbcType arg3) throws SQLException {
        System.out.println("使用我的TypeHandler");
        ps.setString(index, value);
    }

}

