package org.example.dao;

import org.example.model.EkstraPensja;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class EkstraPensjaDAO extends SessionDAO<EkstraPensja> {
    public EkstraPensjaDAO(Session session) {
        super(session);
    }

    @Override
    public void save(EkstraPensja objectToSave) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(objectToSave);
        transaction.commit();
    }

    @Override
    public EkstraPensja get(Serializable id) {
        return session.get(EkstraPensja.class, id);
    }

    @Override
    public List<EkstraPensja> getAll() {
        return session.createQuery(SELECT_ALL_QUERY).getResultList();
    }

    @Override
    public void update(EkstraPensja objectToUpdate) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(objectToUpdate);
        transaction.commit();
    }

    @Override
    public void delete(EkstraPensja objectToDelete) {
        Transaction transaction = session.beginTransaction();
        session.delete(objectToDelete);
        transaction.commit();
    }
}
