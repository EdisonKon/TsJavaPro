package TsDesignModel.responsemodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 14:18
 * @from
 **/
public abstract class ResponseHandel {

    protected final static int NUM_ONE = 1;
    protected final static int NUM_THREE = 3;
    protected final static int NUM_SEVEN = 7;
    //该领导处理的请假天数区间
    private int numStart;
    private int numEnd;

    //设置请假天数范围 上不封顶
    public ResponseHandel(int numStart) {
        this.numStart = numStart;
    }

    //设置请假天数范围
    public ResponseHandel(int numStart, int numEnd) {
        this.numStart = numStart;
        this.numEnd = numEnd;
    }

    private ResponseHandel next;

    public ResponseHandel getNext() {
        return next;
    }

    public void setNext(ResponseHandel next) {
        this.next = next;
    }

    //提交请假条
    public final void submit(LeaveRequest leave){
        if(0 == this.numStart){
            return;
        }

        //如果请假天数达到该领导者的处理要求
        if(leave.getNum() >= this.numStart){
            this.doResp(leave);

            //如果还有上级 并且请假天数超过了当前领导的处理范围
            if(null != this.next && leave.getNum() > numEnd){
                this.next.submit(leave);//继续提交
            } else {
                System.out.println("流程结束");
            }
        }
    }

    protected abstract void doResp(LeaveRequest leave);


    public static void main(String[] args) {
        //请假条来一张
        LeaveRequest leave = new LeaveRequest("小花",5,"身体不适");

        //各位领导
        GroupLeader groupLeader = new GroupLeader();
        Manager manager = new Manager();
        GeneralManager generalManager = new GeneralManager();

        groupLeader.setNext(manager);//小组长的领导是部门经理
        manager.setNext(generalManager);//部门经理的领导是总经理
        //之所以在这里设置上级领导，是因为可以根据实际需求来更改设置，如果实战中上级领导人都是固定的，则可以移到领导实现类中。

        //提交申请
        groupLeader.submit(leave);
    }
}
