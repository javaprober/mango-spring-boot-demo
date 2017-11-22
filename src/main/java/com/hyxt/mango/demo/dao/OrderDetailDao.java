package com.hyxt.mango.demo.dao;

import com.hyxt.mango.demo.dao.entity.OrderDetail;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;

import java.util.List;

/**
 * Created by andy on 2017/11/21.
 */
@DB(table = "t_order_detail")
public interface OrderDetailDao {
    @SQL("insert into #table(id, uid, price, status) values(:id, :uid, :price, :status)")
    public void addOrder(OrderDetail orderDetail);

    @SQL("select id, uid, price, status from #table where uid = :1")
    public List<OrderDetail> getOrdersByUid(int uid);
}


