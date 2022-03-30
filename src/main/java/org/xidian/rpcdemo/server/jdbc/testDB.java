package org.xidian.rpcdemo.server.jdbc;

import java.sql.*;
import java.util.Scanner;

public class testDB {

    public static void main(String[] args) throws InterruptedException {
        Connection connection = null;
        try {
            // create a database connection
            connection = Database.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            Scanner scan = new Scanner(System.in);
            String bookname = scan.next();

            String sql = "select * from book" + " where bookname LIKE '%" + bookname + "%'";

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("bookname = " + rs.getString("bookname"));
                System.out.println("author = " + rs.getString("author"));
            }

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
