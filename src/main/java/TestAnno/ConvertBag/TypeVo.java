package TestAnno.ConvertBag;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-05-16 16:40
 */


public class TypeVo {
    public TypeVo(String name, String tname, String type, String tpx) {
        this.name = name;
        this.tname = tname;
        this.type = type;
        this.tpx = tpx;
    }

    private String name;
    private String tname;
    private String type;
    private String tpx;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTpx() {
        return tpx;
    }

    public void setTpx(String tpx) {
        this.tpx = tpx;
    }
}
