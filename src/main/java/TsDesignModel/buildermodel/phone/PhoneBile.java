package TsDesignModel.buildermodel.phone;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:38
 * @from  构建手机类
 **/
public class PhoneBile {
    private String cpu;
    private String memory;
    private String screen;
    private String battery;


    @Override
    public String toString() {
        return "PhoneBile{" +
                "cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                ", screen='" + screen + '\'' +
                ", battery='" + battery + '\'' +
                '}';
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }
}
