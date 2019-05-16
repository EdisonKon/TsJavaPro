package TestAnno.ConvertBag;

import TestAnno.ReflectProcessor;
import TestAnno.ReflectTest;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-05-16 16:45
 */


public class TestMyConvert {
    private NameVo nameVo = new NameVo("znm",20);
    private TypeVo typeVo = new TypeVo("xnm","mnm","sb","supet");
    private static SomeDTO s = new SomeDTO();
    public static void main(String[] args) {

        MyConvertProcessor r = new MyConvertProcessor();
        try {
            r.parseMethod(TestMyConvert.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s.toString());
    }
}
