package com.hyxt.mango.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by andy on 2017/11/22.
 */
@ConfigurationProperties(prefix = "db1.master")
public class DataSource1MSetting extends DruidSetting {
}
