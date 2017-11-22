package com.hyxt.mango.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by andy on 2017/11/22.
 */
@ConfigurationProperties(prefix = "db0.master")
public class DataSource0Setting extends DruidSetting {
}
