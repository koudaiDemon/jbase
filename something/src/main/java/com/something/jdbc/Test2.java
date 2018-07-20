package com.something.jdbc;

import java.sql.*;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/28  10:48
 */
public class Test2 {

    public static void main(String[] args) throws Exception {

        Class.forName("de.hybris.vjdbc.VirtualDriver");
        final Connection connection = DriverManager.getConnection("jdbc:hybris:sql:http://localhost:9001/virtualjdbc/service?tenant=master","admin","nimda");
        final String sql = "select {name[zh]:o} from {user}";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        final ResultSet resultSet = preparedStatement.executeQuery();
        final ResultSetMetaData metaData = resultSet.getMetaData();
        final int count = metaData.getColumnCount();
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
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

}
