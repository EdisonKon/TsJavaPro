package TestCglib.CglibProxy;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-06 09:51
 * @from
 **/
public class BaseClass {

    public BaseClass() {

    }

    public void saySomething(){
        System.out.println("好好学习,天天向上.");
    }

    public void sayDushu(){
        System.out.println("赶紧学习,好好看书");
    }


    @Test
    public void test() {
        System.out.println("test2");
    }
}
