package org.xidian.rpcdemo.server.controller;

import java.sql.*;
import java.util.ArrayList;

import org.xidian.rpcdemo.server.jdbc.Database;
import org.xidian.rpcdemo.server.model.Book;

public class Operator {

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

    public static ArrayList<Book> queryByName(String bookname) {
        ArrayList<Book> booklist = new ArrayList<Book>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            stmt = conn.createStatement();
            String sql;

            sql = "select * from book" + " where bookname LIKE '%" + bookname + "%'";

            rs = stmt.executeQuery(sql);

            if (rs.next() == false) {
                int ID = -1;
                String bookName = "null";
                String Author = "null";
                Book book = new Book(ID, bookName, Author);
                booklist.add(book);
                return booklist;
            }

            while (rs.next()) {
                int ID = rs.getInt("id");
                String bookName = rs.getString("bookname");
                String Author = rs.getString("author");
                Book book = new Book(ID, bookName, Author);
                booklist.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            booklist = null;
        } finally {
            Database.close(conn, stmt, rs);
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
