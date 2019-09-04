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
package cn.inkroom.log.web.service;

import cn.inkroom.log.web.bean.Statistics;
import cn.inkroom.log.web.dao.StatisticsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author 墨盒
 * @date 19-5-9
 */
@Service
public class StatisticsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StatisticsDao dao;

    public List<String> getTags() {
        return dao.selectTags();
    }

    public List<String> getIP() {
        return dao.selectIP();
    }

    /**
     * 更新记录
     * 在符合条件的最后一条上修改
     *
     * @param tag   tag
     * @param ip    ip
     * @param count 要变化的数据，如+1,-1
     * @param level 级别
     * @return
     */
    public boolean updateCount(String tag, String ip, int level, int count) {


        Statistics statistics = dao.selectLast(tag, ip, level);
        statistics.setCount(statistics.getCount() + 1);
        int res = dao.update(statistics.getId(), tag, ip, level, count);

//        int res = dao.update(tag, ip, level, count);
//        if (res == 0) {//没有数据
//
//            Statistics statistics = new Statistics();
//            statistics.setCount(count);
//            statistics.setLevel(level);
//            statistics.setIp(ip);
//            statistics.setTag(tag);
//
//            return dao.insert(statistics) == 1;
//
//
//        }
        return res == 1;
    }


    public boolean addCount(String tag, String ip, int level, int count) {

        Statistics statistics = new Statistics();
        statistics.setCount(count);
        statistics.setLevel(level);
        statistics.setIp(ip);
        statistics.setTag(tag);

        return dao.insert(statistics) == 1;


    }

    public List<Statistics> list(String tag, String ip, int level, Date start, Date end, int page, int size) {

        List<Statistics> res = dao.select(tag, ip, level, start, end);

//TODO 19-5-9 预留分页功能
        List<Statistics> result = new ArrayList<>(res.size());
        res.forEach(statistics -> {
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).getIp().equals(statistics.getIp()) && result.get(i).getTag().equals(statistics.getTag())
                        && result.get(i).getLevel() == statistics.getLevel()) {
                    result.get(i).setCount(result.get(i).getCount() + statistics.getCount());
                    return;
                }
            }
            result.add(statistics);
        });


        return result;
    }
}
