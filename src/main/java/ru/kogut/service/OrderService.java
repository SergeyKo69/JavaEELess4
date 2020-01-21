package ru.kogut.service;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import ru.kogut.configuration.ConfigHbn;
import ru.kogut.model.dao.OrderDAO;
import ru.kogut.repository.BaseCRUDRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */
public class OrderService implements BaseCRUDRepository<String, OrderDAO> {

    @Inject
    private ConfigHbn configHbn;

    @Override
    public OrderDAO saveOrUpdate(OrderDAO orderDAO) {
        Session session = configHbn.getSession();
        session.beginTransaction();
        session.save(orderDAO);
        session.getTransaction().commit();
        return orderDAO;
    }

    @Override
    public OrderDAO findById(String id) {
        Session session = configHbn.getSession();
        OrderDAO orderDAO;
        try {
            orderDAO = session.load(OrderDAO.class, id);
            orderDAO.getNumber();
        } catch (ObjectNotFoundException e) {
            return null;
        }
        return orderDAO;
    }

    @Override
    public List<OrderDAO> findAll() {
        Session session = configHbn.getSession();
        return session.createQuery("FROM OrderDAO ", OrderDAO.class).list();
    }

    @Override
    public void delete(OrderDAO orderDAO) {
        Session session = configHbn.getSession();
        session.beginTransaction();
        session.delete(orderDAO);
        session.getTransaction().commit();
    }
}
