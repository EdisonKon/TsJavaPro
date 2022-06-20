package TsDesignModel.singleton;


import java.lang.reflect.Constructor;

public enum SingletonEnum {
    SINGLETON;
    class Singleton {
    }
    // 要实例化的对象
    public Singleton instance;

    public Singleton getInstance() {
        return instance;
    }

    private SingletonEnum(){
        instance = new Singleton();
    }

    public static void main(String[] args) throws Exception {
        Singleton o1 = SingletonEnum.SINGLETON.getInstance();
        Singleton o2 = SingletonEnum.SINGLETON.getInstance();
        System.out.println(o1 == o2);
    }

}
