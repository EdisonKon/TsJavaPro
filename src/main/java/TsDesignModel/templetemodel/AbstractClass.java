package TsDesignModel.templetemodel;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 12:33
 * @from 提高代码复用性
 *
 * 将相同部分的代码放在抽象的父类中，而将不同的代码放入不同的子类中。
 *
 * 实现了反向控制
 *
 * 通过一个父类调用其子类的操作，通过对子类的具体实现扩展不同的行为，实现了反向控制 ，并符合“开闭原则”。
 *
 * 缺点：
 *
 * 对每个不同的实现都需要定义一个子类，这会导致类的个数增加，系统更加庞大，设计也更加抽象。
 * 父类中的抽象方法由子类实现，子类执行的结果会影响父类的结果，这导致一种反向的控制结构，它提高了代码阅读的难度。
 * 6.1.4 适用场景
 * 算法的整体步骤很固定，但其中个别部分易变时，这时候可以使用模板方法模式，将容易变的部分抽象出来，供子类实现。
 * 需要通过子类来决定父类算法中某个步骤是否执行，实现子类对父类的反向控制。
 **/
public abstract class AbstractClass {
    // 不可修改的必然顺序
    final void mustOrder(){
        putOil();
        hotOil();
        putVeg();
        putCause();
        cook();
    }

    final void putOil(){
        System.out.println("放油");
    }
    final void hotOil(){
        System.out.println("热油");
    }
    final void cook(){
        System.out.println("翻炒");
    }

    // 放蔬菜
    public abstract void putVeg();

    // 放调料
    public abstract void putCause();

    public static void main(String[] args) {
        AbstractClass abstractClass = new Baocai();
        abstractClass.mustOrder();
    }
}
