package zOther;


/**
 * @author dekai.kong
 * @difficult
 * @create 2020-04-11 13:55
 * @from
 **/
public class TestCompile {
    public int a = 1;
    public int b = 2;

    public int inta(){
        return 1;
    }

    public int intb(){
        return 2;
    }

    public void aandb(){
        System.out.println(a+b);
        System.out.println(inta()+intb());
    }
}
