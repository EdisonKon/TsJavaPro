package TestSpring.MySpring;

public interface MyBeanProcessor {

    public Object beforeBeanProcessor(String beanName , Object bean);

    public Object afterBeanProcessor(String beanName , Object bean);
}
