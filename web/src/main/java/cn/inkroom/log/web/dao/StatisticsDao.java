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
<<<<<<< HEAD
    Statistics selectLast(String tag, String ip, int level);
=======
    Statistics selectLast(@Param("tag") String tag,@Param("ip") String ip,@Param("level") int level);
>>>>>>> 4d1029b3b78a8785d92e47153f90fbe2cf99e0f5

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
