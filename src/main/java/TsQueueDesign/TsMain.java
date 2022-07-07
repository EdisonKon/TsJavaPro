package TsQueueDesign;


import java.util.concurrent.TimeUnit;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-07 16:03
 * @from 用于模拟启动多线程访问
 **/
public class TsMain {

    static QueueDesign qd = new QueueDesign();
    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 4; i++) {
                final String tempi = i+"";
                final int tempj = j;
                new Thread(()->{getValue(tempi);},"新请求 "+tempj+"-"+tempi+" :").start();
            }

            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println("缓存map:" + qd.map);
            System.out.println("第"+(j+1)+"轮====================完成");

        }
        while(true){

        }

    }


    public static void getValue(String key){
        TestMsgEntity msgEntity = qd.getMsgEntity(key);
        System.out.println("结果:" + msgEntity);
    }

}
