package TsDesignModel.responsemodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 14:24
 * @from
 **/
public class GeneralManager extends ResponseHandel{
    public GeneralManager() {
        //部门经理处理7天以上的请假
        super(ResponseHandel.NUM_SEVEN);
    }

    @Override
    protected void doResp(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("总经理审批：同意。");
    }
}
