package TsDesignModel.singleton;


import java.lang.reflect.Constructor;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-20 16:08
 * @from
 **/
public class EnumSingleTest {
    public static void main(String[] args) throws Exception {
        // Singleton这个枚举内部类 会被反射调用新的对象
        Class<?> c = SingletonEnum.Singleton.class;
        Constructor<?> constructor = c.getDeclaredConstructor(SingletonEnum.class);
        constructor.setAccessible(true);
        SingletonEnum.Singleton o1 = (SingletonEnum.Singleton)constructor.newInstance(SingletonEnum.SINGLETON);
        SingletonEnum.Singleton o2 = (SingletonEnum.Singleton)constructor.newInstance(SingletonEnum.SINGLETON);
        System.out.println(o1 == o2);

        // 所以利用枚举的话, 需要单例化的必须是枚举类 才能不被反射生成多对象
        // 也就是枚举类中存在属性,例如User本身就是个枚举类
        // 具体可见 SingletonEnum2
        // TODO 高亮这个才是正确的枚举的singleton 示例

        SingletonEnum2 singleton = SingletonEnum2.SINGLETON;
        singleton.setName("你好");
        singleton.setOk(true);
        System.out.println(singleton);

        SingletonEnum2 singleton2 = SingletonEnum2.SINGLETON;
        singleton2.setName("你好吗2");
        singleton2.setOk(false);
        System.out.println(singleton2);

        System.out.println(singleton == singleton2);

    }
}
