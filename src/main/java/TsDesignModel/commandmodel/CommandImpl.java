package TsDesignModel.commandmodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 13:59
 * @from 命令实现类
 **/
public class CommandImpl implements Command{
    //持有执行命令对象
    private Cheff cheff;
    private Order order;

    public CommandImpl(Cheff cheff, Order order) {
        this.cheff = cheff;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println(order.getTable() + "桌的订单:");
        order.getFoodDir().forEach((key,val)->{cheff.makeFood(key,val);});
        System.out.println(order.getTable() + "桌的订单已经完成");
    }

    public static void main(String[] args) {
        Order order1 = new Order();
        order1.setTable(1);
        order1.setFood("西红柿炒鸡蛋",1);
        order1.setFood("可乐",2);

        Order order2 = new Order();
        order2.setTable(2);
        order2.setFood("小炒肉",1);
        order2.setFood("雪碧",2);

        Cheff cheff = new Cheff();

        Command command1 = new CommandImpl(cheff,order1);
        Command command2 = new CommandImpl(cheff,order2);

        Waitor waitor = new Waitor();
        waitor.setCommand(command1);
        waitor.setCommand(command2);

        waitor.orderUp();


    }
}
