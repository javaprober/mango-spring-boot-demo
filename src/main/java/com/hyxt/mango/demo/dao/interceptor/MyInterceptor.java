package com.hyxt.mango.demo.dao.interceptor;

import org.jfaster.mango.binding.BoundSql;
import org.jfaster.mango.interceptor.Parameter;
import org.jfaster.mango.interceptor.QueryInterceptor;
import org.jfaster.mango.interceptor.UpdateInterceptor;
import org.jfaster.mango.util.jdbc.SQLType;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by andy on 2017/11/21.
 */
public class MyInterceptor {

    public static class MyUpdateInterceptor extends UpdateInterceptor {

        public void interceptUpdate(
                BoundSql boundSql,
                List<Parameter> parameters,
                SQLType sqlType,
                DataSource dataSource) {

//            System.out.println("update sql: " + boundSql.getSql());
            System.out.println("update args: " + boundSql.getArgs());
        }

    }

    public static class MyQueryInterceptor extends QueryInterceptor {

        public void interceptQuery(
                BoundSql boundSql,
                List<Parameter> parameters,
                DataSource dataSource) {

            System.out.println("query sql: " + boundSql.getSql());
            System.out.println("query args: " + boundSql.getArgs());
            System.out.println("dataSource" + dataSource.toString());
        }

    }
}
