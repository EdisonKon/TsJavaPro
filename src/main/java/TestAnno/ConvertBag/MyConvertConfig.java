package TestAnno.ConvertBag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyConvertConfig {
    //映射时候翻转找到的源名和目标名
    //例如 源:name 目标名: zname 就配置成{"name:zname"}
    String[] value() default {};
}
