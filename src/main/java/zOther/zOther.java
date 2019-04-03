package zOther;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-01-24 16:28
 */


public class zOther {
    public static void main(String[] args) {
        Map<String , String> m = new HashMap<>();
        for (int i = 0; i < 10; i++) {
//            cmap(m,i);
        }
        System.out.println(m);
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
}
