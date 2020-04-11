package TsYzTest;


/**
 * @Author: yangzhi
 * @Description: com.test.RingBuffer.Worker
 * @Date: Create in 2020-03-24 1359
 */
public class CommonReadWorker implements Runnable {
    @Override
    public void run() {
        int aCounter = 0;
        while (true) {
            try {
                int size = RingMain.list.size();
                aCounter += size;
                for (int i = 0; i < size; i++) {
                    RingMain.list.remove(0);
                }
                System.out.println("最后的数字为：" + aCounter);
                if (aCounter >= 1000) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
