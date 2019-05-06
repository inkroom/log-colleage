package cn.inkroom.log.web.alert;

import java.util.Properties;

/**
 * 报警方式
 *
 * @author 墨盒
 * @date 19-5-6
 */
public interface MessageAlert {
    void init(Properties properties);

    boolean alert(String message);
}
