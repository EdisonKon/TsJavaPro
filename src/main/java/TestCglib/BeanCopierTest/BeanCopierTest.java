package TestCglib.BeanCopierTest;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-05 19:28
 * @from
 *
 * 用于测试使用cglib的动态代理进行复制对象 深拷贝
 **/
public class BeanCopierTest {
    public BeanCopierTest() {

    }


    @Test
    public void beanCopierTest() {
        Address1 a1 = new Address1("LA", new Date());
        Address1 a2 = new Address1();
        a2 = a1;
        // 这种复制修改会造成a1对象的数据进行修改
        a2.setName("WA");
        System.out.println(a1);

        Address2 a3 = new Address2();
        // 这样就只能使用其他拷贝方法 BeanCopier
        BeanCopier beanCopier = BeanCopier.create(Address1.class,
                Address2.class, true);
        beanCopier.copy(a1, a3, new DateStringConverter());
        System.out.println(a3);
        System.out.println(a1);
    }



    class Address1 {
        private String name;
        private Date date;

        public Address1() {
        }

        public Address1(String name, Date date) {
            super();
            this.name = name;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "名字：" + this.name + " 时间：" + this.date;
        }
    }

    class Address2 {
        private int id;
        private String name;
        private String date;

        public Address2() {
        }

        public Address2(String name, String date) {
            super();
            this.name = name;
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "" + this.id + " 名字：" + this.name + " 时间：" + this.date;
        }
    }

    /**
     *
     * @author Test
     * @createDate 2014-5-31上午10:21:50
     * @className BeanCopierTest.java
     * @useFor 用于转换类型 比如 Address1中的Date类型 转成String
     */
    class DateStringConverter implements Converter {

        @Override
        @SuppressWarnings("all")
        /**
         * @author Test
         * @createDate 2014-5-31
         * @params value是Src对象  target是From对象 context是From类中的方法名
         * @return void
         * @useFor 实现转换方法
         */
        public Object convert(Object value, Class target, Object context) {
            System.out.println(value.getClass() + " " + value); // Src对象
            System.out.println(target); // From对象
            System.out.println(context.getClass() + " " + context); // String对象,具体的方法名
            if (value.getClass().isAssignableFrom(Date.class)) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
            } else {
                return value;
            }
        }

    }
}
