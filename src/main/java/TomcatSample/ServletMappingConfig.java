package TomcatSample;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-03-21 16:28
 */


public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("findGirl","/girl","TomcatSample.FindGirlServlet"));
        servletMappingList.add(new ServletMapping("findGirl","/world","TomcatSample.HelloWorldServlet"));
        servletMappingList.add(new ServletMapping("findGirl","/favicon.ico","TomcatSample.IcoServlet"));
    }
}
