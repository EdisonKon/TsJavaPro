package TestAtomic;


import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-23 16:38
 * @from
 *
 * 测试atomicrefrence
 **/
public class TsAtomicRefrence {
    public static void main(String[] args) {
        Userx userx = new Userx("zzz",10);
        AtomicReference<Userx> atomicReference = new AtomicReference<>(userx);
        System.out.println(atomicReference.get());
        Userx userz = new Userx("xxxx",10);
        System.out.println(atomicReference.compareAndSet(userx, userz));
        System.out.println(atomicReference.get());

    }
}
class Userx{
    private String name;
    private Integer age;

    public Userx(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Userx{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
