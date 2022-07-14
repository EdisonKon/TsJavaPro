package TsDesignModel.factory.configfactory;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 12:04
 * @from
 **/
public abstract class AbstractAnimal implements Animal{

    public String name;

    void getName(){
        System.out.println("我是" + this.name);
    }

    abstract void setName();


}
