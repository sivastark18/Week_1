package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        System.out.println("Welcome to the Library Management System");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.registerBook("War and Peace");
        ((ClassPathXmlApplicationContext) context).close();

        System.out.println("Application execution completed.");
    }
}
