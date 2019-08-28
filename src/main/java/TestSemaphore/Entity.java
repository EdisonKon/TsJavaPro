package TestSemaphore;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-28 16:18
 * @from
 **/
public class Entity {
    private String name;
    public Entity(String name) {
        this.name = name;
    }


    public void say(){
        System.out.println("我是对象:"+name);
    }

    @Test
    public void test() {

    }
}
