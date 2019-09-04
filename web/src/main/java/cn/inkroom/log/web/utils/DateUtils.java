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
package cn.inkroom.log.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期处理工具类，
 * <p>保证线程安全</p>
 *
 * @author 墨盒
 * @Date 19-5-31
 */
public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    public static SimpleDateFormat getFormat(String pattern) {
        ThreadLocal<SimpleDateFormat> local = sdfMap.get(pattern);
        if (local == null) {
            synchronized (lockObj) {
                local = sdfMap.get(pattern);
                if (local == null) {
                    local = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
                    sdfMap.put(pattern, local);
                }
            }
        }
        return local.get();
    }

    public static String format(Date date, String pattern) {
        return getFormat(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) {
        try {
            return getFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            logger.warn("日期数据{} 转换失败,{}", dateStr, e.getMessage());
            return null;
        }
    }
}
