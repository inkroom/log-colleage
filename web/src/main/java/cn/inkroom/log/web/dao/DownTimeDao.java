package cn.inkroom.log.web.dao;

import cn.inkroom.log.web.bean.DownTime;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 墨盒
 * @date 19-6-2
 */
public interface DownTimeDao {

    List<DownTime> selectDown(@Param("ip") String ip, @Param("tag") String tag, @Param("time") Date time);


    int insertDown(@Param("ip") String ip, @Param("tag") String tag, @Param("time") Date time);
}
