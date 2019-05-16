package TestAnno.ConvertBag;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-05-16 16:38
 */


public class SomeDTO {
    private String name;
    private int age;
    private String tname;
    private String tpxDto;

    @MyConvert(froms = "nameVo,typeVo")
    @MyConvertConfig({"tpx:tpxDto"})
    public SomeDTO() {
    }

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

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTpxDto() {
        return tpxDto;
    }

    public void setTpxDto(String tpxDto) {
        this.tpxDto = tpxDto;
    }

    @Override
    public String toString() {
        return "SomeDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", tname='" + tname + '\'' +
                ", tpxDto='" + tpxDto + '\'' +
                '}';
    }
}
