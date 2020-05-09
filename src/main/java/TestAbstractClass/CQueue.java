package TestAbstractClass;

import org.junit.Test;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-05-09 14:43
 * @from
 **/
public class CQueue extends AbstractQueue<Object> {


    private List<Object> queue = new ArrayList<>();

    /**
     * 队列name
     */
    private Object name;

    /**
     * 队列最大长度
     */
    private int length = Integer.MAX_VALUE;

    public CQueue(Object name) {
        this.name = name;
        this.queue.add(name);
    }

    public CQueue() {

    }


    /**
     * 重要! 如果未重写该方法,则在idea的watcher中看到对象是NPE错误,因为该对象返回默认为null,watcher会调用该对象
     * @return
     */
    @Override
    public Iterator<Object> iterator() {
        return queue.iterator();
    }

    @Override
    public int size() {
        return queue.size();
    }


    @Test
    public void test() {

    }

    @Override
    public boolean offer(Object aLong) {
        return false;
    }

    @Override
    public Long poll() {
        return null;
    }

    @Override
    public Long peek() {
        return null;
    }
}
