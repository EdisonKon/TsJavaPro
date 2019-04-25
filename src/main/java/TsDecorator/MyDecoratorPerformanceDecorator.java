package TsDecorator;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-04-25 17:48
 */

/**
 * 订单性能 - 装饰者
 */
public class MyDecoratorPerformanceDecorator implements MyDecorator {
    MyDecorator o;

    public MyDecoratorPerformanceDecorator(MyDecorator o) {
        this.o = o;
    }

    @Override
    public void execute() {
        System.out.println("记录性能-开始");
        o.execute();
        System.out.println("记录性能-结束");
    }
}
