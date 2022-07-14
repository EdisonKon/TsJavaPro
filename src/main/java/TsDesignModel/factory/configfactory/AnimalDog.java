package TsDesignModel.factory.configfactory;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 11:45
 * @from
 **/
public class AnimalDog extends AbstractAnimal{
    @Override
    public void sya() {
        System.out.println("汪汪汪");
    }

    @Override
    void setName() {
        this.name = "狗狗";
    }
}
