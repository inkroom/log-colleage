package cn.inkroom.log.web.alert;

import cn.inkroom.log.web.alert.impl.MailMessageAlert;

import java.util.Properties;

/**
 * @author 墨盒
 * @date 19-5-6
 */
public class MessageAlertFactory {

   public static MessageAlert create(Properties properties) {
        MessageAlert alert = new MailMessageAlert();
        alert.init(properties);
        return alert;
    }
}
