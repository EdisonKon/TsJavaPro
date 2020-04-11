package TsYzTest;


/**
 * @Author: yangzhi
 * @Description: com.test.RingBuffer.CommonWriterWorker
 * @Date: Create in 2020-03-24 1224
 */
public class CommonWriterWorker implements Runnable {
    @Override
    public void run() {
            for(int i=0;i<100;i++)
            {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RingMain.list.add(i);
            }
    }
}
