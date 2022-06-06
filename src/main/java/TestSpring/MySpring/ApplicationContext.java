package TestSpring.MySpring;


import org.apache.commons.lang.StringUtils;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-06-06 16:18
 * @from
 **/
public class ApplicationContext {

    private ConcurrentHashMap<String, MyBeanDifine> myBeanDifineHashMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> myBeanHashMap = new ConcurrentHashMap<>();
    private ArrayList<MyBeanProcessor> processors = new ArrayList<>();


    public ApplicationContext(Class clazz) {
        if (clazz.isAnnotationPresent(MyScan.class)) {
            // 扫描scan
            MyScan myScan = (MyScan)clazz.getAnnotation(MyScan.class);
            String value = myScan.value();
            String replace = value.replace(".","/");
            ClassLoader classLoader = ApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(replace);
            String path = resource.getPath();
            File file = new File(path);
            if (file.isDirectory()) {
                for (File listFile : file.listFiles()) {
                    String fileName = listFile.getPath();
                    if (fileName.endsWith("class")) {
                        try {
                            fileName = fileName.substring(fileName.indexOf("TestSpring"), fileName.lastIndexOf(".class"));
                            fileName = fileName.replace("/",".");
                            Class<?> aClass = classLoader.loadClass(fileName);
                            if (aClass.isAnnotationPresent(MyComponment.class)) {
                                if(MyBeanProcessor.class.isAssignableFrom(aClass)){
                                    processors.add((MyBeanProcessor)aClass.newInstance());
                                }

                                String beanName = aClass.getAnnotation(MyComponment.class).value();
                                beanName = StringUtils.isBlank(beanName)? Introspector.decapitalize(aClass.getSimpleName()) :beanName;
                                MyBeanDifine difine = new MyBeanDifine();
                                difine.setScope("singleton");
                                if(aClass.isAnnotationPresent(MyScope.class)){
                                    difine.setScope(aClass.getAnnotation(MyScope.class).value());
                                }
                                difine.setClassType(aClass);
                                myBeanDifineHashMap.put(beanName, difine);


                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            // 实例化单例bean
            for (Map.Entry<String, MyBeanDifine> entry : myBeanDifineHashMap.entrySet()) {
                String beanName = entry.getKey();
                MyBeanDifine difine = entry.getValue();
                if("singleton".equals(entry.getValue().getScope())){
                    myBeanHashMap.put(beanName, createBean(beanName, difine));
                }
            }
        }

    }

    public Object getBean(String beanName) {
        final MyBeanDifine difine = myBeanDifineHashMap.get(beanName);
        if(difine == null) {
            throw new RuntimeException("cant find beandifine");
        }
        if("singleton".equals(difine.getScope())){
            Object bean = myBeanHashMap.get(beanName);
            if(bean == null){
                bean = createBean(beanName, difine);
            }
            return bean;
        }else{
            return createBean(beanName, difine);
        }
    }

    private Object createBean(String beanName, MyBeanDifine difine) {
        final Class classType = difine.getClassType();
        try {
            // 实例化
            Object instance = classType.getConstructor().newInstance();
            // 依赖注入
            Field[] declaredFields = instance.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(MyAutowired.class)) {
                    Object autoBean = myBeanHashMap.get(declaredField.getName());
                    if(autoBean == null) {
                        autoBean = getBean(declaredField.getName());
                    }
                    declaredField.setAccessible(true);
                    declaredField.set(instance, autoBean);
                }
            }
            //Aware回调赋值
            if ((instance instanceof BeanNameAware)) {
                ((BeanNameAware) instance).setBeanName(beanName+" MySpring define");
            }

            // processor可以处理bean ( before)
            for (MyBeanProcessor processor : processors) {
                processor.beforeBeanProcessor(beanName, instance);
            }

            //Initializing
            if ((instance instanceof MyInitializingBean)) {
                ((MyInitializingBean) instance).afterPropertiesSet();
            }

            // processor可以处理bean ( after)
            for (MyBeanProcessor processor : processors) {
                processor.afterBeanProcessor(beanName, instance);
            }


            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
