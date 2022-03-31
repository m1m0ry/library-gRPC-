package org.xidian.rpcdemo.client.ui;

import java.util.ArrayList;

import org.xidian.rpcdemo.client.model.Book;

public class sampleUI {
    public static void printBook(Book book) {
        String[] header = { "编号", "书名", "作者" };
        String[][] rows = {
                { Integer.toString(book.getBookid()), book.getBookname(), book.getAuthor() }
        };
        System.out.println(new TextTable(header, rows));
    }

    public static void printBook(ArrayList<Book> books) {
        String[] header = { "编号", "书名", "作者" };

        if(null==books) return;
        if(0==books.size()) return;

        String[][] rows = new String[books.size()][];

        int i = 0;

        for (Book book : books) {
            rows[i++] = new String[] { Integer.toString(book.getBookid()), book.getBookname(), book.getAuthor() };
        }

        System.out.println(new TextTable(header, rows));
    }
}
