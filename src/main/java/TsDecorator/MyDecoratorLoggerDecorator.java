package TsDecorator;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-04-25 17:48
 */

/**
 * 订单日志 - 装饰者
 */
public class MyDecoratorLoggerDecorator implements MyDecorator {
    MyDecorator o;

    public MyDecoratorLoggerDecorator(MyDecorator o) {
        this.o = o;
    }

    @Override
    public void execute() {
        System.out.println("记录日志-开始");
        o.execute();
        System.out.println("记录日志-结束");
    }
}
