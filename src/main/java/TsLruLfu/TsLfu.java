package TsLruLfu;

import org.junit.Test;

import java.util.*;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-27 12:18
 * @from lfu 最近最不常使用
 *
 * 用一个map 和 一个linkedList写一个lfu
 * map用于告知当前的key是否存在, 存在在第几个list节点
 * list用于存储真正的kv缓存
 **/
public class TsLfu {

    class LfuNode{
        private String value;
        private String useTime;
    }

    class LfuCache{
        Integer MAX_SIZE = 8;
        // 表示key 在第几个顺序列表里 ,并存在缓存总大小的值
        Map<String, Integer> lfuKeyMap = new HashMap<>(MAX_SIZE);
        // int代表的是顺序节点, 不代表使用次数
        List<Map<String,String>> lfuList = new LinkedList();
        public String getCache(String key){
            if (lfuKeyMap.get(key) == null){
                return null;
            }

            Integer index = lfuKeyMap.get(key);
            String value = lfuList.get(index).get(key);
            putCache(key,value);
            return value;
        }

        public void putCache(String key,String val){
            //如果存在key,则这样处理
            if(lfuKeyMap.get(key)!=null){
                Integer index = lfuKeyMap.get(key);
                lfuList.get(index).remove(key);
                if(lfuList.size() > index+1){
                    Map<String, String> stringStringMap = lfuList.get(index + 1);
                    stringStringMap.put(key,val);
                    lfuList.set(index+1,stringStringMap);
                }else{
                    Map<String, String> stringStringMap = new LinkedHashMap<>();
                    stringStringMap.put(key,val);
                    lfuList.add(stringStringMap);
                }
                lfuKeyMap.put(key,index+1);
            }
            // 如果不存在进行增加或者删除替换,  超过整体缓存数量限制
            else if (lfuKeyMap.size()+1 > MAX_SIZE * 0.75){
                //获取当前最小值的第一个元素
                Map<String, String> stringStringMap = lfuList.get(0);
                String headKey = stringStringMap.entrySet().iterator().next().getKey();
                stringStringMap.remove(headKey);
                stringStringMap.put(key, val);
                lfuKeyMap.put(key,0);
            }else{
                if(lfuList.size() == 0){
                    LinkedHashMap<String, String> kv = new LinkedHashMap<>();
                    kv.put(key,val);
                    lfuList.add(kv);
                }else{
                    LinkedHashMap<String, String> kv = (LinkedHashMap<String, String>) lfuList.get(0);
                    kv.put(key, val);
                    lfuList.set(0,kv);
                }
                lfuKeyMap.put(key,0);
            }
        }
    }

    @Test
    public void test() {
        LfuCache lruCache = new LfuCache();
        lruCache.putCache("1","1");
        lruCache.putCache("2","2");
        lruCache.putCache("1","2");
        lruCache.putCache("3","3");
        lruCache.putCache("4","4");
        lruCache.putCache("5","9");
        lruCache.putCache("6","9");
        lruCache.putCache("7","9");
        lruCache.putCache("8","9");
        lruCache.putCache("8","9");
        lruCache.putCache("8","9");
        lruCache.putCache("9","9");
        lruCache.putCache("8","9");
        lruCache.putCache("7","9");
        lruCache.putCache("10","9");
        System.out.println(lruCache.lfuKeyMap);
        System.out.println(lruCache.lfuList);
    }
}
