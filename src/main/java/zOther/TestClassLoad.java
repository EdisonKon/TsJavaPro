package zOther;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author dekai.kong
 * @difficult
 * @create 2019-12-16 16:10
 * @from
 **/
public class TestClassLoad {
    public TestClassLoad() {

    }

    ArrayList<Integer> arr = new ArrayList<Integer>(100);

    static ArrayList<Integer> arr2 = new ArrayList<Integer>(100);

    static ArrayList<Integer> abcd() {
        ArrayList<Integer> arr = new ArrayList<Integer>(100);
        arr.add(15);
        return arr;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = abcd();
        System.out.println(1);
    }

    @Test
    public void test() {

    }
}
