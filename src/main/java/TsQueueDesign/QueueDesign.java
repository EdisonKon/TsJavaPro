package TsQueueDesign;


import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-07 16:06
 * @from  用于处理并发请求, 队列设计, 10个请求的队列 , 超过丢弃
 * 如果当前缓存中有数据 , 直接获取,不进队列,如果没有 相当于去数据库中获取,此时要进入队列,超过队列的任务进行丢弃,并返回请稍后重试
 **/
public class QueueDesign {

    // 模拟缓存
    Map<String,TestMsgEntity> map = new ConcurrentHashMap<>(16);

    // 用于记录一个缓存key是否存在
    Set<String> mapKeyExist = new ConcurrentSkipListSet();

    ThreadPoolExecutor pool = new ThreadPoolExecutor(
//            Runtime.getRuntime().availableProcessors(), 系统cpu个数
            1,
            2,
            5L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            new DefaultThreadFactory("处理工厂线程"),
            new ThreadPoolExecutor.AbortPolicy()
            );


    public QueueDesign() {

    }

    /**
     * 获取结果方法
     * @param key
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public TestMsgEntity getMsgEntity(String key) {
        TestMsgEntity val = map.get(key);
        if(val!=null){
            System.out.println(Thread.currentThread().getName()+",没进队列直接获取到 "+key+" 缓存"+val);
            return val;
        }else{
            try{
                if(!mapKeyExist.contains(key)){
                    mapKeyExist.add(key);
                    Future<TestMsgEntity> submit = pool.submit(new getValueTask(key));
                    System.out.println("key:"+key +" 被放入队列...");
                    return submit.get();
                }else{
                    System.out.println("key:"+key +" 请求数太多...执行丢弃!!!");
                }
            }catch (Exception e){
                mapKeyExist.remove(key);
                System.out.println("key:"+key +" 被放入队列时队列已满...执行丢弃,界面上弹出稍后再试...");
            }
        }
        return null;
    }


    /**
     * 构建返回结果任务类
     */
    class getValueTask implements Callable<TestMsgEntity>{
        private String key;
        public getValueTask(String key) {
            this.key = key;
        }

        @Override
        public TestMsgEntity call() throws Exception {
            TestMsgEntity val = map.get(key);
            if(val!=null){
                System.out.println(Thread.currentThread().getName()+"获取到 "+key+" 缓存"+val);
                return val;
            }else{
                System.out.println(Thread.currentThread().getName()+"无法获取到 "+key+" 缓存, 正在去数据库中处理...");
                TimeUnit.SECONDS.sleep(1);
                TestMsgEntity msgEntity = new TestMsgEntity();
                msgEntity.setName("aiyo" + Thread.currentThread().getName());
                msgEntity.setAge(10);
                msgEntity.setHobby(new ArrayList<>(Arrays.asList(""+key,"打篮球","游泳",Thread.currentThread().getName())));
                map.put(key, msgEntity);
                mapKeyExist.remove(key);
                System.out.println(Thread.currentThread().getName()+"数据库中处理完成,存入缓存..."+key);
                return msgEntity;
            }
        }
    }

}
