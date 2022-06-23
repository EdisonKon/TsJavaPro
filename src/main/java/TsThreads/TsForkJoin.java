package TsThreads;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-23 10:26
 * @from
 **/
public class TsForkJoin {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 结果:500000000500000000,普通方法用时:3073
         * 结果:500000000500000000,ForkJoin方法用时:1723
         * 结果:500000000500000000,流式并行方法用时:141
         */
        test1(0L,10_0000_0000L);
        test2(0L,10_0000_0000L);
        // 数量级改变后 计算速度变化
        // 10000 - 1833
        // 100000 - 2568
        // 5000-2447
        test3(0L,10_0000_0000L);
    }

    public static Long test1(Long start, Long end){
        Long times = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        Long timee = System.currentTimeMillis();
        System.out.println("结果:"+ sum +",普通方法用时:" + (timee-times));
        return sum;
    }


    public static Long test2(Long start, Long end) throws ExecutionException, InterruptedException {
        Long times = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyForkTask forkTask = new MyForkTask(start,end);
        forkJoinPool.submit(forkTask);
        Long sum  = forkTask.get();
        Long timee = System.currentTimeMillis();
        System.out.println("结果:"+ sum +",ForkJoin方法用时:" + (timee-times));
        return sum;
    }

    public static Long test3(Long start, Long end){
        Long times = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(start,end).parallel().sum();
        Long timee = System.currentTimeMillis();
        System.out.println("结果:"+ sum +",流式并行方法用时:" + (timee-times));
        return sum;
    }


    static class MyForkTask extends RecursiveTask<Long>{
        private Long start;
        private Long end;
        private Long batch = 10000L;

        public MyForkTask(Long start, Long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            Long sum = 0L;
            if(end - start <= batch){
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            }else{
                Long mid = (start + end) / 2;
                MyForkTask myForkTask = new MyForkTask(start, mid);
                MyForkTask myForkTask2 = new MyForkTask(mid+1, end);
                myForkTask.fork();
                myForkTask2.fork();
                sum = myForkTask.join() + myForkTask2.join();
            }
            return sum;
        }
    }
}
