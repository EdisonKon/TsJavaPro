package TestAnno.ConvertBag;

import TestAnno.Reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-03-30 10:55
 */


public class MyConvertProcessor {
    public void parseMethod(final Class<?> clazz) throws Exception{
        Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
//        final Method[] methods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            MyConvert my;
            try{
                Constructor<?>[] cons = field.getType().getClass().getDeclaredConstructors();
                for (Constructor<?> con: cons) {
                    Annotation[] anos =con.getDeclaredAnnotations();
                    my = con.getDeclaredAnnotation(MyConvert.class);
                    if(null != my){
                        field.set("","");
                    }
                }
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println("该属性没有构造函数");
                continue;
            }

        }
    }
}
