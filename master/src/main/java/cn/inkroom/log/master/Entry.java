package cn.inkroom.log.master;

import cn.inkroom.log.model.ActiveMqBean;
import cn.inkroom.log.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 墨盒
 * @Date 18-12-10
 */
public class Entry {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        // 开始读写配置文件

        try {
            Properties properties = new Properties();
            properties.load(new FileReader("config.properties"));


            //读写配置文件，将配置文件和bean对应
            //获取对应的类型

            String type = properties.getProperty("mq.type");

            Object mqConfig = null;

            switch (type) {
                case "activeMq":

                    ActiveMqBean bean = (ActiveMqBean) PropertiesUtil.mapping(properties, "mq", ActiveMqBean.class);

                    mqConfig = bean;
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
