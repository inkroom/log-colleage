package cn.inkroom.log.web.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author 墨盒
 * @date 19-2-9
 */
public class TestDataSourceConfig  {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public DataSource dataSource() {
        logger.debug("自动注入");
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:log_web.sql")
                .build();

    }

//    @Override
//    public DataSource getObject() throws Exception {
//        logger.debug("自动注入");
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScripts("classpath:log_web.sql").build();
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return DataSource.class;
//    }
}
