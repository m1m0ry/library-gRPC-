package org.xidian.rpcdemo.client;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.xidian.rpcdemo.client.controller.Operator;
import org.xidian.rpcdemo.client.model.Book;

public class MyLibGrpcClient {
  public static void main(String[] args) throws InterruptedException {
      Operator.init();

      Book book;

      System.out.println(Operator.add(new Book(7,"线性代数","飞翔")));

      book=Operator.queryByID(3);

      System.out.println("id = " + book.getBookid());
      System.out.println("bookname = " + book.getBookname());
      System.out.println("author = " + book.getAuthor());*/

      for(Book it:Operator.queryByName("科")){
        System.out.println("id = " + it.getBookid());
        System.out.println("bookname = " + it.getBookname());
        System.out.println("author = " + it.getAuthor());
      }

      System.out.println(Operator.delete(7));

      return ;
    }
}