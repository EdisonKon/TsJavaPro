package TestAnno.ConvertBag;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 描述
 * @author: dekai.kong (dekai.kong@luckincoffee.com)
 * @date: 2019-03-30 10:55
 */


public class MyConvertProcessor {

    public void parseMethod(final Class<?> clazz) throws Exception{
        Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        Field[] fields = clazz.getDeclaredFields();
        //这个类里所有其他类对象的map
        Map<String,Field> mapFields = new HashMap<>();
        //所有要注入的dto的map
        Map<String,DtoParam> dtoClasses = new HashMap<>();
        for (Field field : fields) {
            MyConvert my;
            try{
                Class fieldClz = field.getType();
                mapFields.put(field.getName(),field);
                Constructor<?>[] cons = fieldClz.getDeclaredConstructors();
                for (Constructor<?> con: cons) {
                    my = con.getDeclaredAnnotation(MyConvert.class);
                    if(null != my){
                        dtoClasses.put(field.getName(),new DtoParam(con,field));
                    }
                }
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println("该属性没有构造函数");
                continue;
            }
        }
        setValueToDto(dtoClasses,mapFields,obj);
    }

    /**
     * 给dto赋值
     * @param dtoClasses
     * @param mapFields
     * @param obj
     */
    private void setValueToDto(Map<String,DtoParam> dtoClasses,Map<String,Field> mapFields,Object obj){
        Map<String,Object> mapConfigs = new HashMap<>();
        for (Map.Entry<String,DtoParam> entry:dtoClasses.entrySet()) {
            Field dtoField = entry.getValue().getField();
            //是否可访问
            boolean isAcc= dtoField.isAccessible();
            dtoField.setAccessible(true);
            Constructor dtoConstructor = entry.getValue().getConstructor();
            MyConvert my = dtoConstructor.getDeclaredAnnotation(MyConvert.class);
            MyConvertConfig myc = dtoConstructor.getDeclaredAnnotation(MyConvertConfig.class);
            if(myc!=null){
                mapConfigs = configParamParse(myc);
            }
            String forms = my.froms();
            if("".equals(forms)) continue;
            String[] formClassNames = forms.split(",");
            for (String fromName:formClassNames) {
                Field fromField = mapFields.get(fromName);
                if(fromField!=null){
                    try {
                        fromField.setAccessible(true);
                        Object dtoObj = dtoField.get(obj);
                        converJavaBean(dtoObj,fromField.get(obj),mapConfigs);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            //只处理dto
            if(!isAcc){
                dtoField.setAccessible(false);
            }
        }
    }



    /**
     * 解析config也是有重名的保存最后一个设置
     * @param myc
     * @return
     */
    private Map<String,Object> configParamParse(MyConvertConfig myc){
        Map<String,Object> mapConfigs = new HashMap<>();
        String[] configs = myc.value();
        for (String config:configs) {
            String[] kv = config.split(":");
            if(kv.length>1){
                String methodName = "set"+captureName(kv[0]);
                if(kv[1].contains(".")){
                    String[] fromn = kv[1].split("\\.");
                    mapConfigs.put(methodName,new KvEntity(fromn[0],"set"+captureName(fromn[1])));
                }else{
                    mapConfigs.put(methodName,"set"+captureName(kv[1]));
                }
            }
        }
        return mapConfigs;
    }

    //首字母大写
    public static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
    return String.valueOf(cs);
    }



    /**
     * @description 转换javabean ,将class2中的属性值赋值给class1
     * @param class1 基准类,被赋值对象
     * @param class2 提供数据的对象
     * @throws Exception
     */
    public void converJavaBean(Object class1, Object class2, Map<String, Object> mapConfigs) {
        try {
            Class<?> clazz1 = class1.getClass();
            Class<?> clazz2 = class2.getClass();
            // 得到method方法
            Method[] method1 = clazz1.getMethods();
            Method[] method2 = clazz2.getMethods();

            int length1 = method1.length;
            int length2 = method2.length;
            if (length1 != 0 && length2 != 0) {
                // 创建一个set方法数组，专门存放class1的set方法。
                Method[] set = new Method[length1];
                for (int i = 0, j = 0; i < length1; i++) {
                    if (method1[i].getName().indexOf("set") == 0) {
                        set[j] = method1[i];
                        ++j;
                    }
                }

                for (int i = 0; i < set.length; i++) {
                    // 数组初始化的长度多于set方法，所以数组后面的部分是null
                    if (set[i] == null) continue;
                    // 得到set方法的名称 例如：setXxxx
                    String setName = set[i].getName();
                    // 得到get方法的值，判断时候为null，如果为null则进行下一个循环
                    String getName = set[i].getName().replace("set","get");
                    //从配置中获取
                    Object config = mapConfigs.get(setName);
                    if(doConfig(setName,config,clazz1,clazz2,class1,class2)) continue;
                    Method getMethod;
                    try {
                        // 判断在class2中时候有class1中的set值的get方法，如果没有则抛异常进行配置验证
                        getMethod = clazz2.getMethod(getName, new Class[] {});
                    } catch (NoSuchMethodException e) {
                        continue;
                    }
                    Object value = getMethod.invoke(class2, new Object[] {});
                    if (null == value) continue;
                    // 得到set方法的时候传入的参数类型，就是get方法的返回类型
                    Class<?> paramType = getMethod.getReturnType();
                    // 得到class1的set方法，并调用
                    Method setMethod = clazz1.getMethod(setName, paramType);
                    setMethod.invoke(class1, value);
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private boolean doConfig(String setName,Object config,Class<?> clazz1,Class<?> clazz2,Object class1, Object class2){
        if(config!=null){
            if(config instanceof String){
                String getName = config.toString().replace("set","get");
                try{
                    Method getMethod = clazz2.getMethod(getName, new Class[] {});
                    Object value = getMethod.invoke(class2, new Object[] {});
                    Class<?> paramType = getMethod.getReturnType();
                    Method setMethod = clazz1.getMethod(setName, paramType);
                    setMethod.invoke(class1, value);
                }catch (Exception e){
                    return false;
                }
            }else{
                KvEntity kv = (KvEntity)config;
                String className = clazz2.getName();
                if(kv.getKey().equalsIgnoreCase(className.substring(className.lastIndexOf(".")+1))){
                    String getName = ((KvEntity) config).getValue().toString().replace("set","get");
                    try{
                        Method getMethod = clazz2.getMethod(getName, new Class[] {});
                        Object value = getMethod.invoke(class2, new Object[] {});
                        Class<?> paramType = getMethod.getReturnType();
                        Method setMethod = clazz1.getMethod(setName, paramType);
                        setMethod.invoke(class1, value);
                    }catch (Exception e){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    class KvEntity{
        public KvEntity(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
