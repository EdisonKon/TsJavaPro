package TsDesignModel.factory.configfactory;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 11:59
 * @from  借鉴spring的配置加载 config定义的类,直接指定名字即可获取
 **/
public class Client {
    public static void main(String[] args) {
        AnimalConfigFactory factory = new AnimalConfigFactory();
        AbstractAnimal dog = factory.getAnimal("dog");
        dog.setName();
        dog.getName();
        dog.sya();
    }
}
