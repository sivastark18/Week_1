package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the Library Management System\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");
        bookService.addBook("Java Programming Essentials");

        ((ClassPathXmlApplicationContext) context).close();

        System.out.println("\nThank you for using the system.");
    }
}
