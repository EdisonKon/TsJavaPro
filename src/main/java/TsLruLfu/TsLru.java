package TsLruLfu;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-27 12:18
 * @from 尝试用linkedHashMap写lru 最近最少使用
 * 利用linkedhashmap进行排序和替换
 **/
public class TsLru {

    class LruCache{
        Integer MAX_SIZE = 4;
        Map<String, String> lruMap = new LinkedHashMap<>(MAX_SIZE);
        public String getCache(String key){
            if (lruMap.get(key) == null){
                return null;
            }

            putCache(key,lruMap.get(key));

            return lruMap.get(key);
        }

        public void putCache(String key,String val){
            if (lruMap.size()+1 > MAX_SIZE * 0.75){
                String headKey = lruMap.entrySet().iterator().next().getKey();
                lruMap.remove(headKey);
            }
            lruMap.remove(key);
            lruMap.put(key,val);
        }
    }

    @Test
    public void test() {
        LruCache lruCache = new LruCache();
        lruCache.putCache("1","1");
        lruCache.putCache("2","2");
        lruCache.putCache("1","2");
        lruCache.putCache("3","3");
        lruCache.putCache("4","4");
        System.out.println(lruCache.lruMap);
    }
}
