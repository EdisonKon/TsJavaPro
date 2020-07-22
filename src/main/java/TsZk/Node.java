package TsZk;

import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-07-21 15:47
 * @from 测试zk选主
 **/
public class Node {

    private Status status;
    private String nodeForLeaderInfo;
    private ZooKeeper zooKeeper;

    public Node(String listenerNodeForLeader) throws IOException {
        this.nodeForLeaderInfo = listenerNodeForLeader;
        this.zooKeeper = ZooKeeperUtil.getZooKeeper();
        lookingForLeader();
    }

    public void lookingForLeader() {
        status = Status.LOOKING;
        try {
            String leaderInfo = Thread.currentThread().getName();
            // 需要注意这里创建的是临时节点
            System.out.println("我要竞选");
            zooKeeper.create(nodeForLeaderInfo, leaderInfo.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            // 如果上一步没有抛异常，说明自己已经是leader了
            status = Status.LEADER;
            String logMsg = Thread.currentThread().getName() + " is leader";
            System.out.println(logMsg);
        } catch (KeeperException.NodeExistsException e) {
            // 节点已经存在，说明leader已经被别人注册成功了，自己是follower
            status = Status.FOLLOWER;
            try {
                byte[] leaderInfoBytes = zooKeeper.getData(nodeForLeaderInfo, event -> {
                    if (event.getType() == Watcher.Event.EventType.NodeDeleted) {
                        System.out.println("主节点死了...");
                        lookingForLeader();
                    }
                }, null);
                String logMsg = Thread.currentThread().getName() + " is follower, master is " + new String(leaderInfoBytes, "UTF-8");
                System.out.println(logMsg);
            } catch (KeeperException.NoNodeException e1) {
                // 如果在获取leader信息的时候报了节点不存在，说明这个leader比较短命，刚抢到leader就又挂掉了
                lookingForLeader();
            } catch (KeeperException | InterruptedException | UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Status getStatus() {
        return status;
    }

    // 当前节点的状态，节点的状态必须在这三个中的一个
    public enum Status {
        LOOKING, // 选举中
        LEADER, // 选举完毕，当前节点为leader
        FOLLOWER; // 选举完毕，当前节点为follower
    }

}
