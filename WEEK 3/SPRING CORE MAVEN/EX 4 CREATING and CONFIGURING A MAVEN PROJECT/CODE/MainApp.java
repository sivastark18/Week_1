package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        GreetingService greetingService = (GreetingService) context.getBean("greetingService");
        greetingService.sayHello();

        ((ClassPathXmlApplicationContext) context).close();
    }
}
