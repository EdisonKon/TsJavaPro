package TsDecorator;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-04-25 17:53
 */

/**
 * 订单操作实际执行类
 */
public class DoPay implements MyDecorator {
    public DoPay() {
        System.out.println("初始化支付执行");
    }
    @Override
    public void execute() {
        System.out.println("执行支付");
    }
}
