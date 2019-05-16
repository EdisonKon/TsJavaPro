package TestAnno.ConvertBag;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2019-05-16 21:03
 * @from:
 **/

public class DtoParam {
    private Constructor<?> constructor;
    private Field field;

    public DtoParam(Constructor<?> constructor, Field field) {
        this.constructor = constructor;
        this.field = field;
    }

    public Constructor<?> getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
