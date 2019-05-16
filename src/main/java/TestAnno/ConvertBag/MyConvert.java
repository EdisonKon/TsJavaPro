package TestAnno.ConvertBag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyConvert {
    //映射时候从哪个实体来的 例如从nameVo和typeVo来
    //配置成: nameVo,typeVo
    String froms() default "";
}
