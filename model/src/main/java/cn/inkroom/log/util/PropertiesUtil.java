package cn.inkroom.log.util;


import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 将properties属性注入java bean
 *
 * @author 墨盒
 * @date 18-12-10
 */
public class PropertiesUtil {

    /**
     * 将properties注入到bean中
     *
     * @param properties
     * @param prefix     前缀
     * @param value      对应的bean的class对象
     * @return
     */
    public static Object mapping(Properties properties, String prefix, Class value) {

        try {
            Object res = value.newInstance();
            Field[] fields = value.getFields();
            for (int i = 0; i < fields.length; i++) {
                Class fc = fields[i].getClass();
                String key = prefix + "." + fields[i].getName();
                String property = properties.getProperty(key);

                //获取对应的注解
                PropertiesField propertiesField = fields[i].getAnnotation(PropertiesField.class);

                if (propertiesField.required() && property == null) {
                    throw new IllegalArgumentException(key + " 必须有值");
                }
                if (property != null) {
                    if (fc.equals(Integer.class)) {
                        fields[i].set(res, Integer.parseInt(property));
                    } else if (fc.equals(Long.class)) {
                        fields[i].set(res, Long.parseLong(property));
                    } else if (fc.equals(Float.class)) {
                        fields[i].set(res, Float.parseFloat(property));
                    } else if (fc.equals(Double.class)) {
                        fields[i].set(res, Double.parseDouble(property));
                    } else if (fc.equals(Short.class)) {
                        fields[i].set(res, Short.parseShort(property));
                    } else if (fc.equals(Character.class)) {
                        fields[i].set(res, String.valueOf(property).charAt(0));
                    } else if (fc.equals(String.class)) {
                        fields[i].set(res, property);
                    } else {
                        throw new IllegalArgumentException("不支持的class " + fc);
                    }
                }

            }
            return res;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
