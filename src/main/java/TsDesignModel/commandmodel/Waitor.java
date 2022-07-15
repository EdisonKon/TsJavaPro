package TsDesignModel.commandmodel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 14:05
 * @from 服务员类
 **/
public class Waitor {
    // 持有多个命令对象
    private List<Command> commands = new ArrayList<>();
    public void setCommand(Command command){
        commands.add(command);
    }

    public void orderUp(){
        System.out.println("服务员说,新订单来了");
        commands.forEach(Command::execute);
    }

}
