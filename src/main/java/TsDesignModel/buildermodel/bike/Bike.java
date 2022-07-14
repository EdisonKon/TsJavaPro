package TsDesignModel.buildermodel.bike;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:15
 * @from 自行车类
 **/
public class Bike {

    private String frame;
    private String seat;

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "frame='" + frame + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }
}
