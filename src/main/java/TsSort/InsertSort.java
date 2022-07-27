package TsSort;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-26 12:17
 * @from 插入排序
 **/
public class InsertSort {
    public int[] tsInsertSort(int[] ints) {
        if(ints==null || ints.length <2){
            return ints;
        }
        for(int i=1; i< ints.length; i++){
            for (int j = i-1; j >= 0 && ints[j] > ints[j+1]; j--) {
                int temp = ints[j];
                ints[j] = ints[j+1];
                ints[j+1] = temp;
            }
        }
        return ints;
    }


    @Test
    public void test() {

    }
}
