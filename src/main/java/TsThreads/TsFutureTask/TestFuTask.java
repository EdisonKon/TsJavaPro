package TsThreads.TsFutureTask;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveTask;


/**
 * @author dekai.kong
 * @difficult
 * @create 2020-01-15 11:07
 * @from
 **/
public class TestFuTask {
    public TestFuTask() {

    }


    class Task1 implements Callable{
        FutureTask ft2;
        Task1(FutureTask ft2){
            this.ft2 = ft2;
        }
        @Override
        public Object call() throws Exception {
            System.out.println("洗水壶");
            Thread.sleep(1000);
            System.out.println("烧热水");
            Thread.sleep(6000);
            System.out.println("拿到茶叶:"+ft2.get());
            System.out.println("泡茶");
            Thread.sleep(2000);
            return "上茶";
        }
    }

    class Task2 implements Callable{
        String tea = "龙井";
        @Override
        public Object call() throws Exception {
            System.out.println("洗茶杯");
            Thread.sleep(1000);
            System.out.println("洗茶壶");
            Thread.sleep(2000);
            System.out.println("拿茶叶");
            Thread.sleep(2000);
            System.out.println("T2:ok");
            return tea;
        }
    }

    FutureTask ft2 = new FutureTask(new Task2());
    FutureTask ft1 = new FutureTask(new Task1(ft2));


    class SumTask extends RecursiveTask<Long> {

        static final int THRESHOLD = 100;
        long[] array;
        int start;
        int end;

        SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                // 如果任务足够小,直接计算:
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println(String.format("compute %d~%d = %d", start, end, sum));
                return sum;
            }
            // 任务太大,一分为二:
            int middle = (end + start) / 2;
            System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
            SumTask subtask1 = new SumTask(this.array, start, middle);
            SumTask subtask2 = new SumTask(this.array, middle, end);
            invokeAll(subtask1, subtask2);
            Long subresult1 = subtask1.join();
            Long subresult2 = subtask2.join();
            Long result = subresult1 + subresult2;
            System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
            return result;
        }
    }

    @Test
    public void test() {
        Thread t1 = new Thread(ft1);
        Thread t2 = new Thread(ft2);

        t1.start();
        t2.start();

        try {
            System.out.println(ft1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }
}
