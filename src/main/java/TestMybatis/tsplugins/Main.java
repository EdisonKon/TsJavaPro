package TestMybatis.tsplugins;


import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-08 22:16
 * @from
 **/
public class Main {
    public static void main(String[] args) {
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

//            while (true){
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
