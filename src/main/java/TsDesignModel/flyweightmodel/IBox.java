package TsDesignModel.flyweightmodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 12:20
 * @from
 **/
public class IBox extends AbstractBox {

    @Override
    public String getShape() {
        return "I";
    }
}
