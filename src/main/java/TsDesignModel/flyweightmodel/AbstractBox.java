package TsDesignModel.flyweightmodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 12:20
 * @from 俄罗斯方块
 **/
public abstract class AbstractBox {
    public abstract String getShape();

    public void display(String color) {
        System.out.println("方块形状：" + this.getShape() + " 颜色：" + color);
    }
}
