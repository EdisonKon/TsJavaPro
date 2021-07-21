package TestConnection;

import org.junit.Test;

import java.sql.*;
import java.util.Properties;
import java.util.TimeZone;

/**
 * @author dekai.kong
 * @create 2021-07-21 15:15
 **/
public class TrinoConnectionTest {


    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String keytab = this.getClass().getClassLoader().getResource("davinci.keytab").getPath();//获取文件路径
        String jks = this.getClass().getClassLoader().getResource("trino.jks").getPath();//获取文件路径
        Class.forName("io.trino.jdbc.TrinoDriver");
        TimeZone.setDefault(TimeZone.getTimeZone("+08:00"));
        String url = "jdbc:trino://dekai.kong@trinotest01.yummy.tech:8288/hive?SSL=true&KerberosKeytabPath="+keytab+"&KerberosPrincipal=davinci@YUMMY.TECH&KerberosRemoteServiceName=trino&SSLKeyStorePath="+jks+"&SSLKeyStorePassword=Trino123&KerberosUseCanonicalHostname=false";
        Properties properties = new Properties();
        properties.setProperty("user", "dekai.kong");
        properties.setProperty("password", "");
//        properties.setProperty("SSL", "true");
        Connection connection = DriverManager.getConnection(url, properties);
        Statement statement = connection.createStatement();
        String sql = "select * from dwd_yummy.dim_pub_date limit 10";
//        String sql = "select * from ods_yummy_ems_ehr.t_mgt_emp limit 10";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " ||| " +
                    rs.getString(3) + " " + rs.getString(4));
        }

        return connection;
    }
    public TrinoConnectionTest() {

    }


    @Test
    public void test() throws SQLException, ClassNotFoundException {
        getConnection();

    }
}
