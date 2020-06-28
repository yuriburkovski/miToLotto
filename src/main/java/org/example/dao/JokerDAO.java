package org.example.dao;

import org.example.model.Joker;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class JokerDAO extends SessionDAO<Joker>{

    public JokerDAO(Session session) {
        super(session);
    }

    @Override
    public void save(Joker objectToSave) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(objectToSave);
        transaction.commit();
    }

    @Override
    public Joker get(Serializable id) {
        return session.get(Joker.class, id);
    }

    @Override
    public List<Joker> getAll() {
        return session.createQuery(SELECT_ALL_QUERY).getResultList();
    }

    @Override
    public void update(Joker objectToUpdate) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(objectToUpdate);
        transaction.commit();
    }

    @Override
    public void delete(Joker objectToDelete) {
        Transaction transaction = session.beginTransaction();
        session.delete(objectToDelete);
        transaction.commit();
    }
}
