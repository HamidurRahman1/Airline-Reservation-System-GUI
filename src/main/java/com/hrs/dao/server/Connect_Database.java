package com.hrs.dao.server;

import com.hrs.dal.Gateway;

import java.sql.*;

public class Connect_Database {

    public static void main(String args[]) {

        try {

            Connection connection = Gateway.getDBConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from customer_info";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                System.out.println(rs.getString("customer_id") + " " + rs.getString("customer_first_name"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
