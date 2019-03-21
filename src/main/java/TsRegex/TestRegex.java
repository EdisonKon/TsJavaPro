package TsRegex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-01-28 16:48
 */


public class TestRegex {
    public static void main(String[] args) {

        Set<Long> setVals = new HashSet<Long>();
        setVals.addAll(Arrays.asList(00L,11L,22L,33L,44L,55L,66L,77L,88L,99L));
        System.out.println(setVals.contains(13602140154L % 100));
        System.out.println(setVals.contains(13602140144L % 100));
        System.out.println(setVals.contains(13602140133L % 100));
        System.out.println(setVals.contains(13710002166L % 100));

/*
        Pattern patternFilter;
        String regFilter = "^\\d{9}(00|01)$";
//        String regFilter = "^\\d+(00|01)$";
        if("".equals(regFilter)||regFilter ==null){
            patternFilter = null;
        }else{
            patternFilter = Pattern.compile(regFilter);
        }
        System.out.println(patternFilter.matcher("13920106158").matches());
        System.out.println(patternFilter.matcher("13920106100").matches());
        System.out.println(patternFilter.matcher("13920106101").matches());
        System.out.println(patternFilter.matcher("13900000011").matches());

         */
    }

}
