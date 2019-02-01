package cn.inkroom.log.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 墨盒
 * @date 18-12-10
 */
public class PropertiesHandler extends PropertyPlaceholderConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static Properties propertyMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        logger.debug("注入的属性={}", props);
        propertyMap = props;
//        for (Object key : props.keySet()) {
//            String keyStr = key.toString();
//            String value = props.getProperty(keyStr);
//
//            propertyMap.put(keyStr, value);
//        }
    }

    public static Properties getProperties() {
        return propertyMap;
    }

    // static method for accessing context properties
    public static String getProperty(String name) {
        return propertyMap.getProperty(name);
    }
}
