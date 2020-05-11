package TestSync;

/**
 * @Author dekai.kong
 * @description: 难度
 * @create: 2020-05-11 21:54
 * @from:
 **/

public class TestSync {

    public static int index = 0;

    private int indexx = 0;

    public int getIndex() {
        return indexx;
    }

    public void setIndex() {
        synchronized (this){
            this.indexx++;
        }
    }

    public static void setIndexStatic(){
        synchronized (TestSync.class){
            TestSync.index ++;
        }
    }


}
