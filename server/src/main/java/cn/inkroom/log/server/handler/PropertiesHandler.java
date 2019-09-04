/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.inkroom.log.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;

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

        //获取额外定义的配置属性

        File file = new File("conf/config.properties");
        logger.debug("额外配置文件绝对路径={}", file.getAbsolutePath());

        if (file.exists()) {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.debug("额外的配置信息={}", properties);

            properties.forEach(props::put);

            logger.debug("拼接后的配置信息={}", props);

        }


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
