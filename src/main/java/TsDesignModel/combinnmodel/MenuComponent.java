package TsDesignModel.combinnmodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 11:42
 * @from
 **/
public abstract class MenuComponent {
    protected String name;
    protected int level;

    // 添加子节点
    public void add(MenuComponent component){
        throw new UnsupportedOperationException("不支持的添加操作");
    }
    // 删除子节点
    public void delete(MenuComponent component){
        throw new UnsupportedOperationException("不支持的移除操作");
    }
    // 获取子节点
    public MenuComponent getChild(int index){
        throw new UnsupportedOperationException("不支持的获取操作");
    }
    // 获取当前节点名字
    public String getName(){
        return name;
    }

    // 打印菜单目录(包括子菜单)
    public abstract void print();

    public static void main(String[] args) {
        MenuComponent menu = new Menu("父级菜单",1);
        MenuComponent menu1 = new Menu("菜单管理",2);
        menu1.add(new MenuItem("页面访问",3));
        menu1.add(new MenuItem("页面展开",3));
        menu1.add(new MenuItem("页面删除",3));
        MenuComponent menu2 = new Menu("权限管理",2);
        menu2.add(new MenuItem("权限访问",3));
        menu2.add(new MenuItem("权限展开",3));
        menu2.add(new MenuItem("权限删除",3));
        menu.add(menu1);
        menu.add(menu2);

        menu.print();
//        menu1.print();
//        menu2.print();
    }

}
