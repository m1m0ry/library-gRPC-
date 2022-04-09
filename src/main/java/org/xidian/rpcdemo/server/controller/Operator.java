package org.xidian.rpcdemo.server.controller;

import java.sql.*;
import java.util.ArrayList;

import org.xidian.rpcdemo.server.jdbc.Database;
import org.xidian.rpcdemo.server.model.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Operator {

    private static final Logger logger = LoggerFactory.getLogger("Operator.class");

    public static boolean add(int id, String bookname, String author) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Database.getConnection();
            stmt = conn.createStatement();
            String sql = "insert into book values(" + id + ",'" + bookname + "','" + author + "')";
            // System.out.println(sql);
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            Database.close(conn, stmt);
        }
    }

    public static Book queryByID(int id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Book book = null;

        try {
            conn = Database.getConnection();
            stmt = conn.createStatement();
            String sql;

            sql = "select * from book" + " where id='" + id + "'";

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int ID = rs.getInt("id");
                String bookName = rs.getString("bookname");
                String Author = rs.getString("author");
                book = new Book(ID, bookName, Author);
            } else {
                int ID = -1;
                String bookName = "null";
                String Author = "null";
                book = new Book(ID, bookName, Author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.close(conn, stmt, rs);
        }
        return book;
    }

    public static ArrayList<Book> queryByName(String name) {
        ArrayList<Book> booklist = new ArrayList<Book>();
        Connection conn = null;
        Statement stmt_n = null, stmt_a = null;
        ResultSet rs_n = null, rs_a = null;
        int count_n = 0, count_a = 0;
        String sql_n, sql_a;

        try {
            conn = Database.getConnection();
            stmt_n = conn.createStatement();
            stmt_a = conn.createStatement();

            sql_n = "select * from book" + " where bookname LIKE '%" + name + "%'";
            sql_a = "select * from book" + " where author LIKE '%" + name + "%'";

            rs_n = stmt_n.executeQuery(sql_n);
            rs_a = stmt_a.executeQuery(sql_a);

            if (rs_n.isBeforeFirst() == false && rs_a.isBeforeFirst() == false) {
                int ID = -1;
                String bookName = "null";
                String Author = "null";
                Book book = new Book(ID, bookName, Author);
                booklist.add(book);
                return booklist;
            }

            while (rs_n.next()) {
                count_n++;
                int ID = rs_n.getInt("id");
                String bookName = rs_n.getString("bookname");
                String Author = rs_n.getString("author");
                Book book = new Book(ID, bookName, Author);
                booklist.add(book);
            }
            while (rs_a.next()) {
                count_a++;
                int ID = rs_a.getInt("id");
                String bookName = rs_a.getString("bookname");
                String Author = rs_a.getString("author");
                Book book = new Book(ID, bookName, Author);
                booklist.add(book);
            }

            logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "\n" + sql_n + " "
                    + count_n + "\n" + sql_a + " " + count_a + "\ncount: " + booklist.size() + "\n");

        } catch (SQLException e) {
            e.printStackTrace();
            booklist = null;
        } finally {
            Database.close(conn, stmt_n, rs_n);
            Database.close(conn, stmt_a, rs_a);
        }
        return booklist;
    }

    public static boolean delete(int id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Database.getConnection();
            stmt = conn.createStatement();
            String sql;

            sql = "delete from book where id =" + id;

            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.close(conn, stmt);
        }
    }

}
