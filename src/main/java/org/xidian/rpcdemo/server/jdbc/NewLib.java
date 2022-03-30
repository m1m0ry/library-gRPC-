package org.xidian.rpcdemo.server.jdbc;

import java.sql.*;

public class NewLib {

    public static void main(String[] args) throws InterruptedException {
        Connection connection = null;
        try {
            // create a database connection
            connection = Database.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("create table book (id integer, bookname string, author string)");
            statement.executeUpdate("insert into book values(1, '数据科学', '大刘')");
            statement.executeUpdate("insert into book values(2, '走进科技', '张三')");
            statement.executeUpdate("insert into book values(3, '菜谱', '李四')");
            statement.executeUpdate("insert into book values(4, '菜单', '王五')");
            ResultSet rs = statement.executeQuery("select * from book");
            while (rs.next()) {
                // read the result set
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
