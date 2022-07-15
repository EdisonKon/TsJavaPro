package TsDesignModel.flyweightmodel;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-15 12:21
 * @from
 * 一个系统有大量相同或者相似的对象，造成内存的大量耗费。
 * 对象的大部分状态都可以外部化，可以将这些外部状态传入对象中。
 * 在使用享元模式时需要维护一个存储享元对象的享元池，而这需要耗费一定的系统资源，因此，应当在需要多次重复使用享元对象时才值得使用享元模式。
 **/
public class BoxFactory {

    private static HashMap<String, AbstractBox> map;

    private BoxFactory() {
        map = new HashMap<String, AbstractBox>();
        AbstractBox iBox = new IBox();
        AbstractBox lBox = new LBox();
        AbstractBox oBox = new OBox();
        map.put("I", iBox);
        map.put("L", lBox);
        map.put("O", oBox);
    }

    public static final BoxFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final BoxFactory INSTANCE = new BoxFactory();
    }

    public AbstractBox getBox(String key) {
        return map.get(key);
    }


    public static void main(String[] args) {
        BoxFactory factory = BoxFactory.getInstance();
        AbstractBox i = factory.getBox("I");
        i.display("灰色");
        AbstractBox l = factory.getBox("L");
        l.display("绿色");
        AbstractBox o = factory.getBox("O");
        o.display("红色");
        AbstractBox i1 = factory.getBox("I");
        i1.display("黑色");

        // 看看2个对象是否是一个对象 true, 共享对象
        System.out.println(i == i1);




    }
}
