package TsQueueDesign;


import java.util.List;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-07 16:01
 * @from  用于存储消息
 **/
public class TestMsgEntity {

    public TestMsgEntity() {
    }

    public TestMsgEntity(String name) {
        this.name = name;
    }

    private String name;
    private int age;
    private List<String> hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "TestMsgEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby=" + hobby +
                '}';
    }
}
