package TsDesignModel.combinnmodel;

import java.util.List;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 11:47
 * @from
 **/
public class MenuItem extends MenuComponent{


    public MenuItem(String name, int level) {
        this.name = name;
        this.level = level;
    }


    @Override
    public void print() {
        System.out.println(name);
    }
}
