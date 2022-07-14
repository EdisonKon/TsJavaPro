package TsDesignModel.decoratormodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 19:36
 * @from 快餐, 包括价格和描述
 **/
public abstract class FastFood {
    private int price;
    private String desc;

    public FastFood(int price, String desc) {
        this.price = price;
        this.desc = desc;
    }

    public abstract float cost();  //获取价格

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
