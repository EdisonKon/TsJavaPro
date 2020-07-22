package TsZk;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-07-21 15:58
 * @from
 **/
public class LeaderElectionTest {
    private static void sleep(long mils) {
        try {
            TimeUnit.MILLISECONDS.sleep(mils);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        boolean flag = true;
        final String LEADER_INFO_NODE = "/leader-info";
        int nodeNum = 10;
        AtomicLong idGenerator = new AtomicLong();
        AtomicInteger activeNodeCount = new AtomicInteger();
        while (true) {
            if (activeNodeCount.get() >= nodeNum) {
                sleep(10);
                continue;
            }

            // 线程启动需要一定时间，将线程启动看做开机过程，在开机之前就算一台新的机器加入了
            activeNodeCount.incrementAndGet();
            new Thread(() -> {
                try {
                    Node node = new Node(LEADER_INFO_NODE);
                    while (flag) {
                        sleep(1000);
                        // 这里为了试验就让leader有轻微自杀倾向...
                        if (node.getStatus() == Node.Status.LEADER && Math.random() < 0.3) {
                            String logMsg = "----------------------------- " + Thread.currentThread().getName() + " shutdown -----------------------------";
                            System.out.println(logMsg);
                            node.shutdown();
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    activeNodeCount.decrementAndGet();
                }
            }, "node-" + idGenerator.getAndIncrement()).start();
        }
    }
}
