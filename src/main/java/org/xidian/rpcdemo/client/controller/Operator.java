package org.xidian.rpcdemo.client.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.xidian.rpcdemo.client.model.Book;

import org.xidian.rpcdemo.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Operator {

    static ManagedChannel channel = null;
    static BookManagerGrpc.BookManagerBlockingStub stub = null;

    public static void init(String[] args) throws InterruptedException {

        String address = "localhost";
        int port = 8080;

        if (args.length == 2) {
            port = Integer.parseInt(args[1]);
        }
        if (args.length == 3) {
            address = args[1];
            port = Integer.parseInt(args[2]);
        }

        channel = ManagedChannelBuilder.forAddress(address, port)
                .usePlaintext()
                .build();

        stub = BookManagerGrpc.newBlockingStub(channel);

    }

    public static boolean add(Book book) {
        Reply Response = stub.add(
                Bookinfo.newBuilder()
                        .setBookID(BookID.newBuilder().setId(book.getBookid()).build())
                        .setName(BookName.newBuilder().setName(book.getBookname()).build())
                        .setAuthor(book.getAuthor())
                        .build());

        return Response.getStatus();
    }

    public static Book queryByID(int id) {

        Bookinfo Response = stub.queryByID(
                BookID.newBuilder()
                        .setId(id)
                        .build());

        return new Book(Response.getBookID().getId(), Response.getName().getName(), Response.getAuthor());

    }

    public static ArrayList<Book> queryByName(String bookname) {

        ArrayList<Book> booklist = new ArrayList<Book>();

        Iterator<Bookinfo> Response = stub.queryByName(
                BookName.newBuilder()
                        .setName(bookname)
                        .build());

        while (Response.hasNext()) {
            Bookinfo bookinfo = Response.next();
            int ID = bookinfo.getBookID().getId();
            String bookName = bookinfo.getName().getName();
            String Author = bookinfo.getAuthor();
            Book book = new Book(ID, bookName, Author);
            booklist.add(book);
        }

        return booklist;

    }

    public static boolean delete(int id) {
        Reply Response = stub.delete(
                BookID.newBuilder()
                        .setId(id)
                        .build());

        return Response.getStatus();
    }

    public static void shutdown() {
        channel.shutdown();
    }
}
