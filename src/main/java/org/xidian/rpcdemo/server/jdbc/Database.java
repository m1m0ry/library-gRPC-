package org.xidian.rpcdemo.server.jdbc;

import java.sql.*;

public class Database {
    private static Connection connection = null;

    // 对外提供一个方法来获取数据库连接
    public static Connection getConnection() {
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection con, Statement stat, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (stat != null)
                stat.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 重载close函数，适用于没有ResultSet的情况
    public static void close(Connection con, Statement stat) {
        close(con, stat, null);
    }
}
