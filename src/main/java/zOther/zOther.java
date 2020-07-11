package zOther;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-01-24 16:28
 */


public class zOther {
    public static void main(String[] args) {
//        Map<String , String> m = new HashMap<>();
//        for (int i = 0; i < 10; i++) {
////            cmap(m,i);
//        }
//        System.out.println(m);
        float r20 = getRate(20,4716.80f,4.75f);
        float r30 = getRateMore(11,r20);
    }
    @Test
    public void tettt(){
        Map<String , String> m = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            cmap(m,i);
        }
        System.out.println(m);
    }
    public void cmap (Map<String , String> m,int i){
        m.put("i:"+i,i+"");
    }

    @Test
    public  void testSq() throws Exception {
        BlockingQueue<Integer> queue = new SynchronousQueue<>();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(queue.take()+ " 子线程取1");
                    System.out.println(queue.take()+ " 子线程取2");
                    System.out.println(queue.take()+ " 子线程取3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        a.start();
        Thread.sleep(100);
        System.out.println(queue.offer(1) + " 放了1");
        Thread.sleep(100);
        System.out.println(queue.offer(2) + " 放了2");
        Thread.sleep(100);
        System.out.println(queue.offer(3) + " 放了3");
//        System.out.println(queue.take() + " 主线程取");
//        System.out.println(queue.size());


    }

    @Test
    public void testList(){
        List al = new ArrayList();
        al.add("S1");
        al.add("S2");
        al.add("S3");
        al.add("S4");
        for (int i = 0; i < al.size(); i++) {
            al.remove(i);
//            al.set(i,"SS"+i);
        }
        System.out.println(al.toString());
    }
    @Test
    public void upTurnBack(){
        String s = "ADbcjyBkC";
        String tem = "";
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)>='A' && s.charAt(i)<='Z'){
                tem = tem.concat(String.valueOf(s.charAt(i)));
                s = s.substring(0,i).concat(s.substring(i+1));
                i--;
            }
        }
        s = s.concat(tem);
        System.out.println(s);
    }

    //测试银行利率
    public static float getRate(int years,float money,float rate){
        float cur = 0;
        for (int i = 0; i < years; i++) {
            float curMoney = (cur+money);
            cur = curMoney + curMoney*(rate/100);
            System.out.println("第"+(i+1)+"年: "+cur);
        }
        return cur;
    }

    public static float getRateMore(int years,float money){
        float cur = money;
        for (int i = 0; i < years; i++) {
            cur = (float) (cur + cur*(2.75/100));
            System.out.println("第"+(i+1)+"年: "+cur);
        }
        return cur;
    }



}
