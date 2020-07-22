package TsZk;

import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-07-21 15:52
 * @from
 **/
public class ZooKeeperUtil {
    static volatile ZooKeeper zkInstance;
    static Object lock = new Object();

    public static ZooKeeper getZooKeeper(boolean single) throws IOException {
        if(zkInstance == null){
            synchronized (lock){
                if(zkInstance == null){
                    zkInstance = new ZooKeeper("localhost:2181",2000,null);
                    if(zkInstance!=null){
                        return zkInstance;
                    }else{
                        System.out.println("zk 初始化异常");
                        return null;
                    }
                }
            }
        }
        return zkInstance;
    }

    public static ZooKeeper getZooKeeper() throws IOException {
        return new ZooKeeper("localhost:2181",2000,null);
    }
}
