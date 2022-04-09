package org.xidian.rpcdemo.client.ui;

import java.util.ArrayList;
import java.util.Scanner;

import org.xidian.rpcdemo.client.controller.Operator;
import org.xidian.rpcdemo.client.model.Book;

public class sampleMemu {
    public static void memu() {
        Scanner scan = new Scanner(System.in);

        printMenu();

        while (true) {
            // 读取用户输入
            System.out.println("请输入：");
            int choice = scan.nextInt();

            if (5 == choice) {
                out();
                return;
            }
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    queryByID();
                    break;
                case 3:
                    queryByName();
                    break;
                case 4:
                    delete();
                    break;
                default:
                    error();
                    continue;
            }
        }
    }

    static void add() {
        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入 id：");
        int id = Integer.parseInt(scan.nextLine());
        System.out.println("请输入 书名：");
        String name = scan.nextLine();
        System.out.println("请输入 作者：");
        String auuthor = scan.nextLine();

        Book book = new Book(id, name, auuthor);

        sampleUI.printBook(book);

        System.out.println("[Y/N]：");

        String a = scan.nextLine();

        switch (a) {
            case "y":
            case "Y":
            case "yes":
                System.out.println(Operator.add(book));
                break;
        }

        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printMenu();

    }

    static void queryByID() {
        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入 id：");
        int id = Integer.parseInt(scan.nextLine());

        // Book book = new Book(7, "线性代数", "飞翔");
        Book book = Operator.queryByID(id);

        sampleUI.printBook(book);

        System.out.println("返回：");
        scan.nextLine();

        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMenu();
    }

    static void queryByName() {
        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入 书名/作者名：");
        String name = scan.nextLine();

        // ArrayList<Book> books = null;
        ArrayList<Book> books = Operator.queryByName(name);

        sampleUI.printBook(books);

        System.out.println("返回：");
        scan.nextLine();

        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printMenu();
    }

    static void delete() {
        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入 id：");
        int id = Integer.parseInt(scan.nextLine());

        // Book book = new Book(7, "线性代数", "飞翔");
        Book book = Operator.queryByID(id);

        sampleUI.printBook(book);

        System.out.println("[Y/N]：");

        String a = scan.nextLine();

        switch (a) {
            case "y":
            case "Y":
            case "yes":
                System.out.println(Operator.delete(id));
                break;
        }

        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printMenu();
    }

    static void printMenu() {
        String[] header = { "欢迎使用图书管理系统" };
        String[][] rows = {
                { "增加图书...1" },
                { "查询图书...2" },
                { "模糊查询...3" },
                { "删除图书...4" },
                { "退出系统...5" }
        };
        System.out.println(new TextTable(header, rows));
    }

    static void out() {
        System.out.println("成功退出系统，欢迎再次光临！");
        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void error() {
        System.out.println("非法输入");
        try {
            cls();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMenu();
    }

    static void cls() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
