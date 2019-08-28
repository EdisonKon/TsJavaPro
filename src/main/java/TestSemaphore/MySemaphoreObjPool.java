package TestSemaphore;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-28 16:21
 * @from 限流器 + 对象池
 **/
public class MySemaphoreObjPool {

    private List objPool;
    private int size;
    private Class clazz;
    private Semaphore semaphore;
    public MySemaphoreObjPool(int size, Class clazz) {
        this.size = size;
        this.clazz = clazz;
        objPool = new Vector();
        semaphore = new Semaphore(size);
        for (int i = 0; i < size; i++) {
            objPool.add(reflactEntity(clazz,i));
        }

    }

    public void getObjAndDo(String name){
        try {
            semaphore.acquire();
            Object o  = objPool.remove(0);
            Method method = clazz.getMethod(name);
            method.invoke(o);
            Double l = Math.random()*100;
            System.out.println(Thread.currentThread().getName()+"睡:"+l);
            try {
                Thread.sleep(l.longValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            objPool.add(o);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    private Object reflactEntity(Class clazz,int i){
        Object o = null;
        try {
            Constructor c = clazz.getConstructor(String.class);
            o = c.newInstance("name:"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    @Test
    public void test() {

    }
}
