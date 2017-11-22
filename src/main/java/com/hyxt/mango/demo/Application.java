package com.hyxt.mango.demo;

import com.hyxt.mango.demo.dao.interceptor.MyInterceptor;
import org.jfaster.mango.datasource.DataSourceFactory;
import org.jfaster.mango.datasource.MasterSlaveDataSourceFactory;
import org.jfaster.mango.datasource.SimpleDataSourceFactory;
import org.jfaster.mango.operator.Mango;
import org.jfaster.mango.plugin.spring.MangoDaoScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 2017/11/21.
 */
@SpringBootApplication
@Configuration
public class Application {
    private final static String driverName = "com.mysql.jdbc.Driver";

    /*@Bean
    public DataSource dataSource0(
            @Value("${db0.master.url}") String url
            ,@Value("${db0.master.username}") String username
            ,@Value("${db0.master.password}") String password){
        DataSource ds0 = new DriverManagerDataSource(driverName,url,username,password);
        return ds0;
    }*/

    @Bean
    /**
     * 配置简单数据源工厂
     */
    public SimpleDataSourceFactory simpleDataSourceFactory(DataSource dataSource0) {
        SimpleDataSourceFactory simpleDataSourceFactory = new SimpleDataSourceFactory();
        simpleDataSourceFactory.setName("db0");
        simpleDataSourceFactory.setDataSource(dataSource0);
        return simpleDataSourceFactory;
    }

    /*@Bean
    public DataSource dataSource1(
            @Value("${db1.master.url}") String url
            ,@Value("${db1.master.username}") String username
            ,@Value("${db1.master.password}") String password){
        DataSource db1Master = new DriverManagerDataSource(driverName,url,username,password);
        return db1Master;
    }

    @Bean
    public DataSource dataSource1Slave(
            @Value("${db1.slave.url}") String url
            ,@Value("${db1.slave.username}") String username
            ,@Value("${db1.slave.password}") String password){
        DataSource db1Slave = new DriverManagerDataSource(driverName,url,username,password);
        return db1Slave;
    }*/

    @Bean
    /**
     * 配置主从数据源工厂
     */
    public MasterSlaveDataSourceFactory msDataSourceFactory(DataSource dataSource1M
            ,DataSource dataSource1S) {
        //主从库结构
        MasterSlaveDataSourceFactory masterSlaveDataSourceFactory = new MasterSlaveDataSourceFactory();
        masterSlaveDataSourceFactory.setName("db1");
        masterSlaveDataSourceFactory.setMaster(dataSource1M);
        List<DataSource> slaves = new ArrayList<DataSource>();
        slaves.add(dataSource1S);
        masterSlaveDataSourceFactory.setSlaves(slaves);
        return masterSlaveDataSourceFactory;
    }

    /*@Bean
    public DataSource dataSource2(
            @Value("${db2.master.url}") String url
            ,@Value("${db2.master.username}") String username
            ,@Value("${db2.master.password}") String password){
        DataSource db2 = new DriverManagerDataSource(driverName,url,username,password);
        return db2;
    }*/


    @Bean
    /**
     * 配置简单数据源工厂
     */
    public SimpleDataSourceFactory simpleDataSourceFactory2(DataSource dataSource2) {
        SimpleDataSourceFactory simpleDataSourceFactory = new SimpleDataSourceFactory();
        simpleDataSourceFactory.setName("db2");
        simpleDataSourceFactory.setDataSource(dataSource2);
        return simpleDataSourceFactory;
    }

    @Bean
    /**
     * 配置mango对象
     */
    public Mango mango(SimpleDataSourceFactory simpleDataSourceFactory
            ,MasterSlaveDataSourceFactory msDataSourceFactory
            ,SimpleDataSourceFactory simpleDataSourceFactory2){
        Mango mango = Mango.newInstance();
        List<DataSourceFactory> dataSourceFactories = new ArrayList<DataSourceFactory>();
        dataSourceFactories.add(simpleDataSourceFactory);
        dataSourceFactories.add(msDataSourceFactory);
        dataSourceFactories.add(simpleDataSourceFactory2);
        mango.setDataSourceFactories(dataSourceFactories);
        mango.addInterceptor(new MyInterceptor.MyQueryInterceptor());
        mango.addInterceptor(new MyInterceptor.MyUpdateInterceptor());
        return mango;
    }

   /* @Bean
    *//**
     * 单库
     *//*
    public Mango singleMango(SimpleDataSourceFactory simpleDataSourceFactory){
        Mango mango = Mango.newInstance();
        List<DataSourceFactory> dataSourceFactories = new ArrayList<DataSourceFactory>();
        dataSourceFactories.add(simpleDataSourceFactory);
        mango.setDataSourceFactories(dataSourceFactories);
        mango.addInterceptor(new MyInterceptor.MyQueryInterceptor());
        mango.addInterceptor(new MyInterceptor.MyUpdateInterceptor());
        return mango;
    }*/

    @Bean
    /**
     * 配置扫描使用@DB注解修饰的DAO类
     */
    public MangoDaoScanner mangoDaoScanner(){
        MangoDaoScanner mangoDaoScanner = new MangoDaoScanner();
        List<String> packages = new ArrayList<String>();
        packages.add("com.hyxt.mango.demo.dao");
        mangoDaoScanner.setPackages(packages);
        return mangoDaoScanner;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
