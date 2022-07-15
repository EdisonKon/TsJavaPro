package TsDesignModel.commandmodel;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 13:55
 * @from 订单类
 **/
public class Order {
    private int table;
    private Map<String, Integer> foodDir = new HashMap<>(16);

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public Map<String, Integer> getFoodDir() {
        return foodDir;
    }

    public void setFood(String name, Integer number) {
        this.foodDir.put(name,number);
    }
}
