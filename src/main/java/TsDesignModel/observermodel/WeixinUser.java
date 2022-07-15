package TsDesignModel.observermodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 14:54
 * @from
 **/
public class WeixinUser implements Observer {
    // 微信用户名
    private String name;

    public WeixinUser(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }

    public static void main(String[] args) {
        SubscriptionSubject mSubscriptionSubject=new SubscriptionSubject();
        //创建微信用户
        WeixinUser user1=new WeixinUser("孙悟空");
        WeixinUser user2=new WeixinUser("猪悟能");
        WeixinUser user3=new WeixinUser("沙悟净");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        mSubscriptionSubject.notify("您订阅的的专栏更新了");

        mSubscriptionSubject.detach(user2);

        System.out.println("==========二次更新===========");
        mSubscriptionSubject.notify("您订阅的的专栏更新了");
    }
}
