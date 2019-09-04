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
