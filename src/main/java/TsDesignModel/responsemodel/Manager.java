package TsDesignModel.responsemodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 14:23
 * @from
 **/
public class Manager extends ResponseHandel {
    public Manager() {
        //部门经理处理3-7天的请假
        super(ResponseHandel.NUM_THREE, ResponseHandel.NUM_SEVEN);
    }

    @Override
    protected void doResp(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("部门经理审批：同意。");
    }
}
