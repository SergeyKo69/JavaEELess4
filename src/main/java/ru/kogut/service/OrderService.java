package ru.kogut.service;

import ru.kogut.model.dao.OrderDAO;
import ru.kogut.repository.BaseCRUDRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */

@Named
@Transactional
@ApplicationScoped
public class OrderService implements BaseCRUDRepository<String, OrderDAO>, Serializable {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @Override
    public void saveOrUpdate(OrderDAO orderDAO) {
        OrderDAO order = em.find(OrderDAO.class, orderDAO.getId());
        if (order == null) {
            em.persist(orderDAO);
        } else {
            mapOrderDAO(order, orderDAO);
            em.merge(order);
        }
    }

    @Override
    public OrderDAO findById(String id) {
        return em.find(OrderDAO.class, id);
    }

    @Override
    public List<OrderDAO> findAll() {
        return em.createQuery("FROM OrderDAO", OrderDAO.class).getResultList();
    }

    //Тут пусть будет по комментарию
    @Override
    public List<OrderDAO> findByName(String title) {
        return em.createQuery("from OrderDAO where comment LIKE ?1", OrderDAO.class)
                .setParameter(1, title).getResultList();
    }

    @Override
    public void delete(OrderDAO orderDAO) {
        OrderDAO order = em.find(OrderDAO.class, orderDAO.getId());
        if (order != null) {
            em.remove(order);
        }
    }

    public void mapOrderDAO(OrderDAO in, OrderDAO out) {
        in.setNumber(out.getNumber());
        in.setDate(out.getDate());
        in.setTotalAmount(out.getTotalAmount());
        in.setComment(out.getComment());
        in.setOrderTabList(out.getOrderTabList());
    }

}
