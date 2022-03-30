package org.xidian.rpcdemo.server;

import org.xidian.rpcdemo.grpc.*;
import org.xidian.rpcdemo.server.controller.Operator;
import org.xidian.rpcdemo.server.model.Book;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;

public class MyLibGrpcServer {
    static public void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new BookManagerImpl()).build();

        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started!");
        server.awaitTermination();
    }

    public static class BookManagerImpl extends BookManagerGrpc.BookManagerImplBase {

        public void add(Bookinfo request, StreamObserver<Reply> responseObserver) {

            System.out.println(request);

            Reply response = Reply.newBuilder()
                    .setStatus(
                            Operator.add(request.getBookID().getId(), request.getName().getName(), request.getAuthor()))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        public void queryByID(BookID request, StreamObserver<Bookinfo> responseObserver) {

            System.out.println(request);

            Book book = Operator.queryByID(request.getId());

            Bookinfo response = Bookinfo.newBuilder()
                    .setBookID(BookID.newBuilder().setId(book.getBookid()).build())
                    .setName(BookName.newBuilder().setName(book.getBookname()).build())
                    .setAuthor(book.getAuthor())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        public void queryByName(BookName request, StreamObserver<Bookinfo> responseObserver) {

            System.out.println(request);

            ArrayList<Book> booklist = Operator.queryByName(request.getName());

            for (Book book : booklist) {
                Bookinfo response = Bookinfo.newBuilder()
                        .setBookID(BookID.newBuilder().setId(book.getBookid()).build())
                        .setName(BookName.newBuilder().setName(book.getBookname()).build())
                        .setAuthor(book.getAuthor())
                        .build();
                responseObserver.onNext(response);
            }

            responseObserver.onCompleted();
        }

        public void delete(BookID request, StreamObserver<Reply> responseObserver) {

            System.out.println(request);

            Reply response = Reply.newBuilder().setStatus(Operator.delete(request.getId())).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

    }

}
