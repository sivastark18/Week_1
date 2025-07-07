package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.example.model.Employee; 

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration().configure();

           
            config.addAnnotatedClass(Employee.class);

            sessionFactory = config.buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace(); // Print the real error
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
