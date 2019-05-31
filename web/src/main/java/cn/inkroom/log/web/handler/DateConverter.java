package cn.inkroom.log.web.handler;

import cn.inkroom.log.web.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;


/**
 * @author 墨盒
 * @Date 19-5-31
 */
public class DateConverter implements Converter<String, Date> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String pattern = "yyyy-MM-dd HH:mm";

    @Override
    public Date convert(String s) {
        logger.debug("转换日期对象,{}", s);
        return DateUtils.parse(s, pattern);
    }
}
