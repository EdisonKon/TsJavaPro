package TsDesignModel.combinnmodel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 11:47
 * @from
 **/
public class Menu extends MenuComponent{

    private List<MenuComponent> childs = new ArrayList<>();

    public Menu(String name,int level) {
        this.name = name;
        this.level = level;
    }

    // 添加子节点
    @Override
    public void add(MenuComponent component){
        childs.add(component);
    }
    // 删除子节点
    @Override
    public void delete(MenuComponent component){
        childs.remove(component);
    }
    // 获取子节点
    @Override
    public MenuComponent getChild(int index){
        return childs.get(index);
    }

    @Override
    public void print() {
        System.out.println(name);
        for (int i = 0; i < childs.size(); i++) {
            String x = childs.get(i).name;
            for (int j = 0; j < childs.get(i).level; j++) {
                x = "-" + x;
            }
            childs.get(i).name = x;
            childs.get(i).print();
        }
    }
}
