package TestAnno;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-03-30 10:59
 */


public class ReflectTest {
    @Reflect
    public static void sayHello(final String name){
        System.out.println("helloDefault,"+name);
    }

    @Reflect(name = "mnm")
    public static void sayHelloOther(final String name){
        System.out.println("helloOther,"+name);
    }

    public static void main(String[] args) throws Exception{
        final  ReflectProcessor r = new ReflectProcessor();
        r.parseMethod(ReflectTest.class);
    }
}
