package TestAnno.ConvertBag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyConvertConfig {
    /**
     * 映射时候翻转找到的源名和目标名
     * 例如 源:zname 目标名:name  就配置成{"zname:name"}  意义是   (什么):(来源于什么)
     * 如指定从那个类里获取某个值则用. 例如{"zname:nameVo.name"}
     * 如果有多个属性名,但是未指定从哪个获取,例如from的类里有2个name (nameVo的name typeVo的name)那默认顺序获取 获取到最后一个相同的
     */
    String[] value() default {};
}
