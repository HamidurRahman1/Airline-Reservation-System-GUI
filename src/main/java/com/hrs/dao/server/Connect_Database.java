package com.hrs.dao.server;

import java.sql.*;

public class Connect_Database {

    public static void main(String args[]) {

        String url = "jdbc:mysql://localhost:3306/AirlineReservationDataBase";
        String user = "root";
        String pass = "*Codarx1971#";

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
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
