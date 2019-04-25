package TsDecorator;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-04-25 16:46
 */


public class TsDecoratorMain {
    public static void main(String[] args) {

        //先使用日志进行装饰,再使用性能进行装饰
        MyDecorator myd = new MyDecoratorLoggerDecorator(new MyDecoratorPerformanceDecorator(new DoOrder()));
        myd.execute();

        //只使用日志进行装饰
        MyDecorator myd2 = new MyDecoratorLoggerDecorator(new DoPay());
        myd2.execute();
    }
}
