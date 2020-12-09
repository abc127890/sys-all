package com.sys.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源
 *
 * @author wfd
 */
@Configuration
public class DataSourceConfiguration {

    @Bean("sys.hikariConfig")
    @ConfigurationProperties("sys.datasource")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean("sys.dataSource")
    public DataSource dataSource() {
        HikariConfig config = hikariConfig();
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
