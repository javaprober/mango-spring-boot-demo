package com.hyxt.mango.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by andy on 2017/11/22.
 */
//@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableConfigurationProperties({DataSource0Setting.class,DataSource2Setting.class,DataSource1MSetting.class,DataSource1SSetting.class,})
public class DruidDataSourceConfig {

    @Bean(destroyMethod = "close" , initMethod = "init")
    public DataSource dataSource0(DataSource0Setting dataSource0Setting) throws SQLException {
        return dataSourceConfig(dataSource0Setting);
    }

    @Bean(destroyMethod = "close" , initMethod = "init")
    public DataSource dataSource1M(DataSource1MSetting dataSource1MSetting) throws SQLException {
        return dataSourceConfig(dataSource1MSetting);
    }

    @Bean(destroyMethod = "close" , initMethod = "init")
    public DataSource dataSource1S(DataSource1SSetting dataSource1SSetting) throws SQLException {
        return dataSourceConfig(dataSource1SSetting);
    }

    @Bean(destroyMethod = "close" , initMethod = "init")
    public DataSource dataSource2(DataSource2Setting dataSource2Setting) throws SQLException {
        return dataSourceConfig(dataSource2Setting);
    }

    private DataSource dataSourceConfig(DruidSetting ds) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource(false);
        if(StringUtils.isNotEmpty(ds.getId())){
            druidDataSource.setName(ds.getId());
        }
        druidDataSource.setUsername(ds.getUsername());
        druidDataSource.setUrl(ds.getUrl());
        druidDataSource.setPassword(ds.getPassword());
        druidDataSource.setFilters(ds.getFilters());
        druidDataSource.setMaxActive(ds.getMaxActive());
        druidDataSource.setInitialSize(ds.getInitialSize());
        druidDataSource.setMaxWait(ds.getMaxWait());
        druidDataSource.setMinIdle(ds.getMinIdle());
        druidDataSource.setTimeBetweenEvictionRunsMillis(ds.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(ds.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(ds.getValidationQuery());
        druidDataSource.setTestWhileIdle(ds.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(ds.isTestOnBorrow());
        druidDataSource.setTestOnReturn(ds.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(ds.isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(ds.getMaxOpenPreparedStatements());
        return druidDataSource;
    }
}
