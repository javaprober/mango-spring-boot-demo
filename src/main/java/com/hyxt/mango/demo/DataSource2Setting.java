package com.hyxt.mango.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by andy on 2017/11/22.
 */
@ConfigurationProperties(prefix = "db2.master")
public class DataSource2Setting extends DruidSetting {
}
