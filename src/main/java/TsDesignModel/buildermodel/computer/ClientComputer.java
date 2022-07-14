package TsDesignModel.buildermodel.computer;


/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 14:01
 * @from
 **/
public class ClientComputer {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
                .cpu("intel")
                .memory("海盗船")
                .build();
        System.out.println(computer);
    }
}
