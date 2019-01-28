package TsRegex;

import java.util.regex.Pattern;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-01-28 16:48
 */


public class TestRegex {
    public static void main(String[] args) {
        Pattern patternFilter;
        String regFilter = "^\\d{9}(00|01)$";
        if("".equals(regFilter)||regFilter ==null){
            patternFilter = null;
        }else{
            patternFilter = Pattern.compile(regFilter);
        }
        System.out.println(patternFilter.matcher("13920106158").matches());
        System.out.println(patternFilter.matcher("13920106100").matches());
        System.out.println(patternFilter.matcher("13920106101").matches());
        System.out.println(patternFilter.matcher("13900000011").matches());
    }
}
