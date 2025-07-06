package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        System.out.println("Library Management - Starting");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");
        bookService.registerBook("The Fault in our stars");

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("Library Management - Finished");
    }
}
