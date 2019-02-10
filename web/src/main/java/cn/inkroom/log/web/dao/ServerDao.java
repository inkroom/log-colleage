package cn.inkroom.log.web.dao;

import cn.inkroom.log.model.Server;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 墨盒
 * @date 19-2-9
 */
public interface ServerDao {
    /**
     * 根据ip查找对应数据
     *
     * @param ip ip
     * @return server
     * @throws Exception 异常
     */
    Server selectServerByIp(@Param("ip") String ip) throws Exception;

    /**
     * 获取所有server
     *
     * @return server
     * @throws Exception 数据库异常
     */
    List<Server> selectServer() throws Exception;

    /**
     * 根据ip更新状态
     *
     * @param server server带有ip
     * @return 受影响行数
     * @throws Exception 数据库异常
     */
    int updateServer(@Param("s") Server server) throws Exception;

    /**
     * 添加server
     *
     * @param server server
     * @return 受影响行数
     * @throws Exception 异常
     */
    int insertServer(@Param("s") Server server) throws Exception;

    /**
     * 将所有server置为宕机状态
     *
     * @return 受影响行数
     * @throws Exception 异常
     */
    int updateAllServerStop() throws Exception;

    /**
     * 根据ip地址删除
     *
     * @param ip ip
     * @return 受影响行数
     * @throws Exception 异常
     */
    int deleteServerByIp(@Param("ip") String ip) throws Exception;
}
