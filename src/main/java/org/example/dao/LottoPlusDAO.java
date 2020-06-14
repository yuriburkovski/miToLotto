package org.example.dao;

import org.example.model.LottoPlus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.model.LottoPlus;

import java.io.Serializable;
import java.util.List;

    public class LottoPlusDAO extends SessionDAO<LottoPlus> {

        public LottoPlusDAO(Session session) {
            super(session);
        }

        @Override
        public void save(LottoPlus objectToSave) {
            Transaction transaction = session.beginTransaction();
            session.save(objectToSave);
            transaction.commit();
        }

        @Override
        public LottoPlus get(Serializable id) {
            return session.get(LottoPlus.class, id);
        }

        @Override
        public List<LottoPlus> getAll() {
            return session.createQuery(SELECT_ALL_QUERY).getResultList();
        }

        @Override
        public void update(LottoPlus objectToUpdate) {
            Transaction transaction = session.beginTransaction();
            session.update(objectToUpdate);
            transaction.commit();
        }

        @Override
        public void delete(LottoPlus objectToDelete) {
            Transaction transaction = session.beginTransaction();
            session.delete(objectToDelete);
            transaction.commit();
        }

    }

