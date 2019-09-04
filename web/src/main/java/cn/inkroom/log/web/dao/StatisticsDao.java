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
package cn.inkroom.log.web.dao;


import cn.inkroom.log.web.bean.Statistics;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 墨盒
 * @date 19-5-9
 */
public interface StatisticsDao {

    /**
     * 判断指定统计信息是否存在
     *
     * @param tag   tag
     * @param ip    ip
     * @param level level
     * @return
     */
    Statistics selectLast(@Param("tag") String tag,@Param("ip") String ip,@Param("level") int level);

    /**
     * 更新数据
     *
     * @param id    仅不为-1才会作为判断条件
     * @param tag
     * @param ip
     * @param level
     * @param count 要变化的数量，在原有基础上做累加操作
     * @return
     */
    int update(@Param("id") int id, @Param("tag") String tag, @Param("ip") String ip, @Param("level") int level, @Param("count") int count);

    /**
     * 添加数据
     *
     * @param statistics 数据
     * @return
     */
    int insert(@Param("s") Statistics statistics);

    /**
     * 查找
     *
     * @param tag   null所有
     * @param ip    null代表所有
     * @param level -1代表所有
     * @return
     */
    List<Statistics> select(@Param("tag") String tag, @Param("ip") String ip, @Param("level") int level, @Param("start") Date start, @Param("end") Date end);

    /**
     * 获取现有的tag
     *
     * @return
     */
    List<String> selectTags();

    /**
     * 获取现有的ip
     *
     * @return
     */
    List<String> selectIP();
}
