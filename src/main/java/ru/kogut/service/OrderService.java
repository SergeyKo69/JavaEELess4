package ru.kogut.service;

import ru.kogut.model.dao.OrderEntity;
import ru.kogut.repository.BaseCRUDRepository;
import ru.kogut.service.interfaces.OrderInt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
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

@Stateless
@TransactionAttribute
public class OrderService implements OrderInt, Serializable {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @Override
    public void saveOrUpdate(OrderEntity orderDAO) {
        OrderEntity order = em.find(OrderEntity.class, orderDAO.getId());
        if (order == null) {
            em.persist(orderDAO);
        } else {
            mapOrderDAO(order, orderDAO);
            em.merge(order);
        }
    }

    @Override
    public OrderEntity findById(String id) {
        return em.find(OrderEntity.class, id);
    }

    @Override
    public List<OrderEntity> findAll() {
        return em.createQuery("FROM OrderEntity", OrderEntity.class).getResultList();
    }

    //Тут пусть будет по комментарию
    @Override
    public List<OrderEntity> findByName(String title) {
        return em.createQuery("from OrderEntity where comment LIKE ?1", OrderEntity.class)
                .setParameter(1, title).getResultList();
    }

    @Override
    public void delete(OrderEntity orderDAO) {
        OrderEntity order = em.find(OrderEntity.class, orderDAO.getId());
        if (order != null) {
            em.remove(order);
        }
    }

    public void mapOrderDAO(OrderEntity in, OrderEntity out) {
        in.setNumber(out.getNumber());
        in.setDate(out.getDate());
        in.setTotalAmount(out.getTotalAmount());
        in.setComment(out.getComment());
        in.setOrderTabList(out.getOrderTabList());
    }

}
