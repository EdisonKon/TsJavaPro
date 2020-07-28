package TsJol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-07-02 17:52
 * @from
 **/
public class TestJol {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Person p = new Person();
//        synchronized (p){

            System.out.println(ClassLayout.parseInstance(p).toPrintable());
//        }

    }

    static class Person{
        int age;
        Integer a;
//        float name;
//        Object object;
        Boolean x;
        boolean z;
//        long a,b,c,d,e,f,g,h;
    }
}
