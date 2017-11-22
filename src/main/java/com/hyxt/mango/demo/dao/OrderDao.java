package com.hyxt.mango.demo.dao;

import com.hyxt.mango.demo.dao.entity.Order;
import com.hyxt.mango.demo.dao.sharding.OrderSharding;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.Sharding;
import org.jfaster.mango.annotation.ShardingBy;

import java.util.List;

/**
 * Created by andy on 2017/11/21.
 */
@DB(table = "t_order")
public interface OrderDao {
    @SQL("insert into #table(id, uid, price, status) values(:id, :uid, :price, :status)")
    @Sharding(shardingStrategy = OrderSharding.OrderUidShardingStrategy.class)
    public void addOrder(@ShardingBy("uid") Order order);

    @SQL("select id, uid, price, status from #table where uid = :1")
    @Sharding(shardingStrategy = OrderSharding.OrderUidShardingStrategy.class)
    public List<Order> getOrdersByUid(@ShardingBy int uid);

    @SQL("select id, uid, price, status from #table where id = :1")
    @Sharding(shardingStrategy = OrderSharding.OrderIdShardingStrategy.class)
    public Order getOrderById(@ShardingBy String id);
}


