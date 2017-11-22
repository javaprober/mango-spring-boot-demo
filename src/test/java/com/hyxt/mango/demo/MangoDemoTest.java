package com.hyxt.mango.demo;

import com.google.common.collect.Lists;
import com.hyxt.mango.demo.dao.OrderDao;
import com.hyxt.mango.demo.dao.OrderDetailDao;
import com.hyxt.mango.demo.dao.entity.Order;
import com.hyxt.mango.demo.dao.entity.OrderDetail;
import com.hyxt.mango.demo.dao.interceptor.MyInterceptor;
import com.hyxt.mango.demo.util.RandomUtils;
import org.jfaster.mango.operator.Mango;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by andy on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TransactionConfiguration(defaultRollback = false)
public class MangoDemoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    DataSource dataSource0;

    @Test
    public void orderTest(){
        List<Integer> uids = Lists.newArrayList(1, 2, 3, 30001, 30002, 30003);
        for (Integer uid : uids) {
            String randomId = RandomUtils.randomStringId(10); // 随机生成10位字符串ID
            String holderMark = "1"; // 订单ID首位永远为1，避免为0
            String databaseMark = uid % 3 + "";
            String tableMark = uid <= 1000 ? "0" : "1";
            String id = holderMark + databaseMark + tableMark + randomId; // ID前添加分库分表标记
            Order order = new Order();
            order.setId(id);
            order.setUid(uid);
            order.setPrice(100);
            order.setStatus(1);

            orderDao.addOrder(order);
            System.out.println(orderDao.getOrdersByUid(uid));
            System.out.println(orderDao.getOrderById(id));
        }
    }


    @Test
    public void simpleDbOperate() {
        Mango mango = Mango.newInstance(dataSource0);
        mango.addInterceptor(new MyInterceptor.MyQueryInterceptor());
        mango.addInterceptor(new MyInterceptor.MyUpdateInterceptor());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId("2");
        orderDetail.setPrice(100);
        orderDetail.setStatus(1);
        orderDetail.setUid(0);
        OrderDetailDao orderDetailDao = mango.create(OrderDetailDao.class);
        orderDetailDao.addOrder(orderDetail);
    }
}
