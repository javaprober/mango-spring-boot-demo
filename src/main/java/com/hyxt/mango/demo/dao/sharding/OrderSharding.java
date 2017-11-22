package com.hyxt.mango.demo.dao.sharding;

import org.jfaster.mango.sharding.ShardingStrategy;

/**
 * Created by andy on 2017/11/21.
 */
public class OrderSharding {

    public static class OrderUidShardingStrategy implements ShardingStrategy<Integer, Integer> {

        @Override
        public String getDataSourceFactoryName(Integer uid) {
            return "db" + uid % 3;
        }

        @Override
        public String getTargetTable(String table, Integer uid) {
            int num = uid <= 1000 ? 0 : 1;
            return table + "_" + num;
        }

    }

    public static class OrderIdShardingStrategy implements ShardingStrategy<String, String> {

        @Override
        public String getDataSourceFactoryName(String orderId) {
            return "db" + orderId.substring(1, 2);
        }

        @Override
        public String getTargetTable(String table, String orderId) {
            return table + "_" + orderId.substring(2, 3);
        }

    }
}
