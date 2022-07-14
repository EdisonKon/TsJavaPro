package TsDesignModel.factory.configfactory;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 11:48
 * @from
 **/
public class AnimalConfigFactory {

    private static Map<String ,Animal> mapAni = new HashMap<>(16);

    static{
        Properties properties = new Properties();
        InputStream resourceAsStream = AnimalConfigFactory.class.getClassLoader().getResourceAsStream("configfactory.properties");
        try {
            properties.load(resourceAsStream);
            for (Map.Entry<Object, Object> objectEntry : properties.entrySet()) {
                String clazzNam = (String) objectEntry.getKey();
                String clazzStr = (String) objectEntry.getValue();

                Class<?> aClass = Class.forName(clazzStr);
                Animal ani = (Animal) aClass.newInstance();

                mapAni.put(clazzNam,ani);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public Animal getAnimal(String name){
        return mapAni.get(name);
    }

}
