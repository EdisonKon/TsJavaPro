package TestAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 描述 注解测试
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-03-30 10:48
 */

@Target(ElementType.METHOD) //应用到方法
@Retention(RetentionPolicy.RUNTIME) //注解会在class字节码文件中存在，在运行时可以通过反射获取到。
public @interface Reflect {
    String name() default "znm";
}
