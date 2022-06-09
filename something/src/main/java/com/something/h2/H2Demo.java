package com.something.h2;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Date;


/**
 * 测试H2,
 * 1.创建表,
 * 2.执行插入语句,
 * 3.执行查询语句
 *
 * @author wei.cai@hand-china.com 2022/6/9
 */
@Slf4j
public class H2Demo {

    public static void main(String[] args) throws Exception {
        final Connection connection = DriverManager.getConnection("jdbc:h2:~/h2/test");

        connection.setAutoCommit(true);

        final PreparedStatement preparedStatement = connection.prepareStatement("create table test( id bigint, code varchar(255), name varchar(255), birthday datetime);");
        boolean flag = preparedStatement.execute();
        log.info("create flag:[{}]", flag);

        final PreparedStatement insert = connection.prepareStatement("insert into test(id, code, name, birthday) values (?,?,?,?)");

        insert.setInt(1, 1);
        insert.setString(2, "NAME");
        insert.setString(3, "CODE");
        insert.setObject(4, new Date());

        final int insertResult = insert.executeUpdate();
        log.info("insertResult:[{}]", insertResult);

        final PreparedStatement select = connection.prepareStatement("select id, code, name, birthday from test");
        final ResultSet resultSet = select.executeQuery();

        while (resultSet.next()) {
            final int id = resultSet.getInt("id");
            final String code = resultSet.getString("code");
            final String name = resultSet.getString("name");
            final Timestamp birthday = resultSet.getTimestamp("birthday");
            final Date date = new Date(birthday.getTime());

            log.info("record:[id:{},code:{},name:{},birthday:{}]", id, code, name, date);
        }


        while (true) {

        }
    }

}
