package cn.inkroom.log.server.dao.impl;


import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.server.dao.LogDao;
import cn.inkroom.log.server.handler.DbConnectFactory;
import cn.inkroom.log.server.util.NotNullMap;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author 墨盒
 * @date 19-1-8
 */
@Repository
public class JdbcLogDao implements LogDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DbConnectFactory factory;

    public JdbcLogDao(){
        logger.debug("dao层被注入");
    }

    @Override
    public int insert(LogMsg msg) throws Exception {

        Map<String, String> tags = new NotNullMap<>();

        tags.put("ip", msg.getIp());
        tags.put("tag", msg.getTag());
//        tags.put("time", msg.getTime().toString());

        Map<String, Object> fields = new NotNullMap<>();
        fields.put("msg", msg.getMsg());

        factory.insert("log", tags, fields, msg.getTime().getTime());

        return 1;
    }

    private String getValueFromInfluxList(Object value) {
        if (value == null) return null;
        return value.toString();
    }

    @Override
    public List<LogMsg> selectByTime(long start, long end) throws Exception {
//influxdb 判断时间需要加上单位，因为默认纳秒级别
        QueryResult result = factory.query("select * from log where time >= " + start + "ms and time <= " + (end) + "ms");
        if (result.hasError()) return null;
        logger.debug("results ={}", result.getResults());
        List<QueryResult.Result> list = result.getResults();


        if (list.size() > 0 && list.get(0).getSeries() != null) {//有数据
            List<LogMsg> msgs = new ArrayList<>(list.get(0).getSeries().size());

            List<List<Object>> values = list.get(0).getSeries().get(0).getValues();
            for (List<Object> value : values) {
                LogMsg msg = new LogMsg();
                int i = 0;
                logger.debug("value={}", value);
                //本身时间是一个double类型
                msg.setTime(new Date(((Double) value.get(i++)).longValue()));
//                logger.debug("class={},time={}", value.get(i).getClass(), value.get(i++).toString());
                msg.setMsg(getValueFromInfluxList(value.get(i++)));
                msg.setTag(getValueFromInfluxList(value.get(i++)));
                msg.setIp(getValueFromInfluxList(value.get(i++)));

                msgs.add(msg);
            }
            logger.debug("获取的数据={}", msgs);
            return msgs;

        }
//        for (QueryResult.Result res : list) {
//
//            List<QueryResult.Series> series = res.getSeries();
//
//            logger.debug("获取的values={}", series.get(0).getValues());
//            logger.debug("获取的columns={}", series.get(0).getColumns());
//            logger.debug("获取的tags={}", series.get(0).getTags());
//        }

        return null;
    }


}
