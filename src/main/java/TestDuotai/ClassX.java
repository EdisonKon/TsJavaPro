package TestDuotai;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-02 19:34
 * @from
 *
 * 测试多态
 **/
class ClassX {
    public void mTwo() {
        System.out.println("p22");
    }
    public void mThree() {
        System.out.println("33");
    }

}
class ClassY extends ClassX{
    public void mOne() {
        System.out.println("11");
    }
    @Override
    public void mTwo() {
        System.out.println("22");
        super.mTwo();
    }
}

class ClassZ {
    public static void main(String[] args) {
        ClassX ob2 = new ClassY();
        ((ClassY) ob2).mOne();
        ob2.mTwo();
        ob2.mThree();

        ClassY ob3 = new ClassY();
        ob3.mOne();
        ob3.mTwo();
        ob3.mThree();
    }
}
