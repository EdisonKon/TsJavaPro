package TestCycleImport;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-22 17:27
 * @from
 **/
public class zTestCycleImport {
    public zTestCycleImport() {

    }


    @Test
    public void test() {

    }

    @Test
    public void buidDog() {
        Dog newDog = new Dog();
        Tail newTail = new Tail();
        newDog.tail = newTail;
        newTail.dog = newDog;

        System.out.println(JSON.toJSONString(newDog));
    }

    public int testReturn(){
        try{
            return 1;
        }catch (Exception e){

        }finally {
            return 2;
        }
    }
    @Test
    public void ttttt(){
        System.out.println(testReturn());
    }

    public static void main(String[ ] args) {
        Float totalMoney = 200000.8f;
        Float ownMoney = 170000.5f ;
        Float leftMoney = totalMoney - ownMoney ;
        System. out . println("剩余" + leftMoney ) ;
    }
}
