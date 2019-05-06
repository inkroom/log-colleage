package cn.inkroom.log.web.check;

import cn.inkroom.log.web.check.impl.ErrorLevelAlert;

import java.util.Properties;

/**
 * @author 墨盒
 * @date 19-5-6
 */
public class CheckLogFactory {

   public static CheckLog create(Properties properties) {
        ErrorLevelAlert alert = new ErrorLevelAlert();
        return alert;
    }
}
