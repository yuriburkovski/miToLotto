package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class App
{
    private static SessionFactory factory;

    public static void main( String[] args ){

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }
}
