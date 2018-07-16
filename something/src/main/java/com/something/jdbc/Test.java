package com.something.jdbc;

import java.sql.*;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/28  10:48
 */
public class Test {

    public static void main(String[] args) throws Exception {

        Class.forName("de.hybris.vjdbc.VirtualDriver");
        Connection connection = DriverManager.getConnection("jdbc:hybris:sql:http://localhost:9001/virtualjdbc/service?tenant=master","admin","nimda");
        String sql = "select * from users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int count = metaData.getColumnCount();
        for (int i = 1 ; i <= count ; i++) {
            System.out.print(metaData.getColumnName(i)+"##");
        }
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1 ; i <= count ; i++) {
                System.out.print(resultSet.getObject(i)+"##");
            }
            System.out.println();
        }
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        preparedStatement.execute();
//        ResultSetMetaData metaData = preparedStatement.getMetaData();
//        System.out.println(metaData.getColumnCount());
//        StreamingResultSet resultSet = (StreamingResultSet)preparedStatement.executeQuery();
//        System.out.println(resultSet.getMetaData().getColumnCount());
//        System.out.println(resultSet.getString("p_uid"));
//        System.out.println();
//        System.out.println();
//        resultSet.next();
//        System.out.println();
//        resultSet.getArray(0);

//        System.out.println(resultSet);
//        while (resultSet.next()) {
//            String str = resultSet.getString(1);
//            System.out.println(str);
//        }
//        resultSet.close();
//        preparedStatement.close();
        connection.close();
    }

}
