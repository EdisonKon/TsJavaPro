package TomcatSample;

import java.io.IOException;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-03-21 17:19
 */


public class IcoServlet  extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get ico...");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post ico...");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
