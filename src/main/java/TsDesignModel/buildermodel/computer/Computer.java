package TsDesignModel.buildermodel.computer;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 13:57
 * @from 直接使用对象类进行builder
 **/
public class Computer {
    private String cpu;
    private String memory;


    public Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.memory = builder.memory;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }

    public static class Builder{
        private String cpu;
        private String memory;

        public Builder cpu(String cpu){
            this.cpu = cpu;
            return this;
        }
        public Builder memory(String memory){
            this.memory = memory;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }

    }
}
