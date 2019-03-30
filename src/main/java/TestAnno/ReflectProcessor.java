package TestAnno;

import java.lang.reflect.Method;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-03-30 10:55
 */


public class ReflectProcessor {
    public void parseMethod(final Class<?> clazz) throws Exception{
        final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method:methods) {
            final  Reflect my = method.getAnnotation(Reflect.class);
            if(null != my){
                method.invoke(obj,my.name());
            }
        }
    }
}
