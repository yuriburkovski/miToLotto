package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.example.dao.LottoDAO;
import org.example.model.Lotto;

import java.util.Date;
import java.util.List;

public class App {
    private static SessionFactory factory;

    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        LottoDAO lottoDAO = new LottoDAO(session);

        Lotto lotto = lottoDAO.get(0);

//        List<Lotto> listaLosowawn = lottoDAO.getAll();
//        for (Lotto lotto : listaLosowawn) {
//            System.out.println(lotto);
//        }

        lottoDAO.close();
        factory.close();
    }
}
