package org.example.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.model.Mini;

import java.io.Serializable;
import java.util.List;

public class MiniDAO extends SessionDAO<Mini> {

    public MiniDAO(Session session) {
        super(session);
    }

    @Override
    public void save(Mini objectToSave) {
        Transaction transaction = session.beginTransaction();
        session.save(objectToSave);
        transaction.commit();
    }

    @Override
    public Mini get(Serializable id) {
        return session.get(Mini.class, id);
    }

    @Override
    public List<Mini> getAll() {
        return session.createQuery(SELECT_ALL_QUERY).getResultList();
    }

    @Override
    public void update(Mini objectToUpdate) {

    }

    @Override
    public void delete(Mini objectToDelete) {

    }

}
