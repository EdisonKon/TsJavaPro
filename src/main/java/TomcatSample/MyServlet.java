package TomcatSample;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-03-21 15:43
 */


public abstract class MyServlet {

    public abstract void doGet(MyRequest myRequest,MyResponse myResponse);

    public abstract void doPost(MyRequest myRequest,MyResponse myResponse);

    public void service(MyRequest myRequest,MyResponse myResponse){
        if(myRequest.getMethod().equalsIgnoreCase("POST")){
            doPost(myRequest,myResponse);
        }else if(myRequest.getMethod().equalsIgnoreCase("GET")){
            doGet(myRequest,myResponse);
        }
    }
}
