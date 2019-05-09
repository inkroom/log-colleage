package cn.inkroom.log.web.dao;


import cn.inkroom.log.web.bean.Statistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 墨盒
 * @date 19-5-9
 */
public interface StatisticsDao {

//    /**
//     * 判断指定统计信息是否存在
//     *
//     * @param tag   tag
//     * @param ip    ip
//     * @param level level
//     * @return
//     */
//    int exist(String tag, String ip, int level);

    /**
     * 更新数据
     *
     * @param tag
     * @param ip
     * @param level
     * @param count 要变化的数组，在原有基础上做累加操作
     * @return
     */
    int update(@Param("tag") String tag, @Param("ip") String ip, @Param("level") int level, @Param("count") int count);

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
    List<Statistics> select(@Param("tag") String tag, @Param("ip") String ip, @Param("level") int level);

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
