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
 * @Date 19-1-8
 */
public class DbConnectFactory {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String username;
    private String password;
    private String url;
    private String database;

    private String time = "5d";//保存数据时长

    private InfluxDB db;

    public DbConnectFactory(String username, String password, String url, String database, String time) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.database = database;
        this.time = time;

        db = InfluxDBFactory.connect(url, username, password);
        db.setDatabase(database);

        // TODO: 19-1-8 保留策略有问题

        String command = String.format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s DEFAULT",
                "default", database, time, 1);
        this.query(command);

    }

    public QueryResult query(String command) {
        //指定单位毫秒，否则输出rfc3339_date_time_string 格式时间，且用UTC，比北京时间早八个小时
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


        db.write(database, "", builder.build());
    }
}
