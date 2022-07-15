package TsDesignModel.responsemodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 14:22
 * @from
 **/
public class GroupLeader extends ResponseHandel{
    public GroupLeader() {
        //小组长处理1-3天的请假
        super(ResponseHandel.NUM_ONE, ResponseHandel.NUM_THREE);
    }

    @Override
    protected void doResp(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("小组长审批：同意。");
    }
}
