package TsCycleBarrier;


import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-07-07 14:16
 * @from
 **/
public class TsCycleBarrier {

    public static void main(String[] args) throws InterruptedException {
        new ReentrantLock();
        new StampedLock();
        Map map = new LinkedHashMap(16,0.75f,true);
        map.put(1,66);
        map.put(2,266);
        map.put(3,366);


        String a = "1234";
        char[] ax = a.toCharArray();
        String rs = "";
        for (int i = ax.length-1; i >= 0; i--) {
            rs+=ax[i];
        }
        System.out.println("结果:"+rs);

        map.get(2);
        map.get(1);
        for(Object mapx :map.entrySet()){
            System.out.println(mapx);
        }
        System.out.println(JSON.toJSONString(map));

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("我是屏障");
        });

        for (int i = 0; i < 2; i++) {
            System.out.println("第"+i+"轮");
            for (int j = 0; j < 3; j++) {
                new Thread(()->{
                    System.out.println("到达");
                    try {
                        int rand = new Random().nextInt(500);
                        System.out.println("等一下"+rand);
                        Thread.sleep((long)Math.random()*2000);
                        cyclicBarrier.await();
                        System.out.println("继续"+rand);

//                        int rand2 = new Random().nextInt(500);
//                        System.out.println("再等一下"+rand2);
//                        Thread.sleep((long)Math.random()*1000);
//                        cyclicBarrier.await();
//                        System.out.println("再继续"+rand2);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }


                }).start();
            }
//            Thread.sleep(3000);
        }

//        System.out.println("完事了");
    }
}
