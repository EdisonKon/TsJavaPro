package TsDesignModel.singleton;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-13 19:40
 * @from
 **/
public class SingletonInnerClass {

    private SingletonInnerClass() {
    }

    public static SingletonInnerClass getInstant(){
        return InnerClass.INSTANCE;
    }

    private static class InnerClass{
        private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
    }
}
