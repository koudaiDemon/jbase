package com.something.jdbc;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/20  20:47
 */
public class Test3 {

    public static void main(String[] args) throws Exception {

        Class.forName("org.hsqldb.jdbcDriver");

        Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:/u01/app/ip2/hybris/data/hsqldb/mydb;shutdown=true;hsqldb.tx=MVCC","SA","");

//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(1) FROM products");
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        final ResultSetMetaData metaData = resultSet.getMetaData();
//        final int count = metaData.getColumnCount();
//        for (int i = 1 ; i <= count ; i++) {
//            System.out.print(metaData.getColumnName(i)+"##");
//        }
//        System.out.println();
//        while (resultSet.next()) {
//            for (int i = 1 ; i <= count ; i++) {
//                System.out.print(resultSet.getObject(i)+"##");
//            }
//            System.out.println();
//        }
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();

//        PreparedStatement preparedStatement = connection.prepareStatement("ALTER TABLE mailboxcontrollp ALTER COLUMN p_emailcontent LONGVARCHAR");

//        preparedStatement.execute();


    }

}
