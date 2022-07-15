package TsDesignModel.facademodel;


/**
 * 智能app控制类
 */
public class SmartApplicationFacade {

    private Light light;
    private Television tele;

    public SmartApplicationFacade() {
        this.light = new Light();
        this.tele = new Television();
    }

    public void smartSay(String str){
        if(str.equals("打开")){
            on();
            System.out.println("统一打开了");
        }else if(str.equals("关闭")){
            off();
            System.out.println("统一关闭了");
        }else {
            System.out.println("我不知道你在说什么");
        }

    }

    private void on() {
        light.on();
        tele.on();
    }

    private void off() {
        light.off();
        tele.off();
    }


    public static void main(String[] args) {
        SmartApplicationFacade facade = new SmartApplicationFacade();
        facade.smartSay("打开");
        facade.smartSay("关闭");
    }
}
