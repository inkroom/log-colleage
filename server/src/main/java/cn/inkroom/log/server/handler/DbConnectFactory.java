package cn.inkroom.log.server.handler;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 墨盒
 * @date 19-1-8
 */
public class DbConnectFactory {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String username;
    private String password;
    private String url;
    private String database;

    private String time;//保存数据时长

    private String policy = "default";
    private InfluxDB db;

    public DbConnectFactory(String username, String password, String url, String database, String time) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.database = database;
        this.time = time;

        db = InfluxDBFactory.connect(url, username, password);
        db.setDatabase(database);
        //TODO 19-2-10 只有默认策略才能插入数据，需要研究一下

// 非默认策略需要在write时制定策略名称，默认策略来自于创建数据库，只能alter，不能create
        String command = String.format("ALTER RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s",
                policy, database, this.time, 1);

        logger.info("创建策略{}={}", policy, command);
        this.query(command);

    }

    public QueryResult query(String command) {
        //TODO 19-2-10 influxdb 自带的web时区有问题（test插入正确，web查询显示错误），但是直接query时区没有问题
        //指定单位毫秒，否则输出rfc3339_date_time_string 格式时间，且用UTC，比北京时间早八个小时
        logger.debug("influxdb sql语句={}", command);
        return db.query(new Query(command, database), TimeUnit.MILLISECONDS);
    }

    /**
     * 插入
     *
     * @param measurement 表
     * @param tags        标签
     * @param fields      字段
     * @param timestamp   时间戳
     */
    public void insert(String measurement, Map<String, String> tags, Map<String, Object> fields, long timestamp) {
        Point.Builder builder = Point.measurement(measurement);
        builder.tag(tags);
        builder.fields(fields);

        builder.time(timestamp, TimeUnit.MILLISECONDS);

        db.write(database, policy, builder.build());
    }
}
