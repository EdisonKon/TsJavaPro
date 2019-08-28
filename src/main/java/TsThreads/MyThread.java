package TsThreads;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-08-28 19:19
 * @from
 **/
public class MyThread extends Thread{

    @Override
    public void run(){
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("i am waiting but facing interruptexception now");
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    @Test
    public void test() {

    }

    public static void main(String[] args) throws Exception{
        //waiting 状态时候,设置中断会抛出interrupt异常 此时会清空标志位 并停止线程
        Thread thread = new MyThread();
        thread.start();

        Thread.sleep(500);
        System.out.println(thread.getState());
        thread.interrupt();
        Thread.sleep(1000);
        System.out.println(thread.isInterrupted());
        while(true){
            Thread.sleep(100);
            System.out.println(thread.getState());
        }

        /**
         * 输出
         * WAITING -------进入同步块  此时 waitting
         * i am waiting but facing interruptexception now --------被中断,抛出异常
         * false -------清空了中断标志位
         * TIMED_WAITING --------sleep 是有时限的waitting
         * RUNNABLE -------睡醒之后 继续执行 变成了runnable
         * TERMINATED   ------执行完成 线程结束
         * TERMINATED
         */
    }
}
