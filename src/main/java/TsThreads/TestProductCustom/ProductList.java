package TsThreads.TestProductCustom;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-07-30 17:04
 * @from
 **/
public class ProductList {

    public volatile int limit = 3;

    public List<String> list = new ArrayList<>(limit);

}
