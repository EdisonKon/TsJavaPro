package TsDesignModel.factory.abstractfactory;


/**
 * 抽象工厂方法, 就是把工厂抽象出来
 * 把产品也抽象出来
 * 一个品牌的一组 叫作产品族(例如小米路由器和小米手机是一个族的)
 * 一个类型的一组 叫作产品等级 (例如小米手机 和 华为手机是一个级别  但是与路由器是不同级别)
 */
public interface IProductFactory {
    IrouterFactory getRouter();
    IphoneFactory getPhone();
}
