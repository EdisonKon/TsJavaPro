package TestMybatis.tscommonplugin;

import TestMybatis.tsplugins.Role;
import TestMybatis.tsplugins.RoleMapper;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class MainTest {

    @Test
    public void test() throws NoSuchMethodException {
        Method encryptStr = AnalysisUtil.class.getMethod("encryptStr", String.class);
        Method decryptStr = AnalysisUtil.class.getMethod("decryptStr", String.class);

        KeyMethodMapping.keyInMethod.put("password",encryptStr);
        KeyMethodMapping.keyInMethod.put("roleName",this.getClass().getMethod("selfMethod1",String.class));
        KeyMethodMapping.keyOutMethod.put("password",decryptStr);
        KeyMethodMapping.keyOutMethod.put("roleName",this.getClass().getMethod("selfMethod2",String.class));


        String resource= "TestMybatis/mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=null;
        try {
            sqlSession=sqlSessionFactory.openSession();
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            Role role=roleMapper.getRole(1L);
//            Role role=roleMapper.getRole2(1L);
            System.out.println(JSON.toJSONString(role));
            Role role2 = new Role();
            BeanUtils.copyProperties(role,role2);
            role2.setPassword("567");
            int i = roleMapper.insertRole(role2);
            System.out.println(JSON.toJSONString(role2));

            sqlSession.commit();

            while(true){}
        } catch (Exception e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }

    public String selfMethod1(String oldVal){
        return "自定义方法1:" + oldVal;
    }

    public String selfMethod2(String oldVal){
        return "自定义方法2:" + oldVal;
    }
}
