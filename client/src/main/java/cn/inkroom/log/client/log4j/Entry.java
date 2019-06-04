package cn.inkroom.log.client.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 墨盒
 * @Date 19-6-4
 */
public class Entry {

    private static Logger logger = LoggerFactory.getLogger(Entry.class);

    public static void main(String[] args) {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                logger.info(args[i]);
            }
        }
        System.exit(0);
    }
}
