package ru.kogut.repository;

import ru.kogut.model.dao.OrderEntity;
import ru.kogut.service.interfaces.OrderInt;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author S.Kogut on 09.02.2020
 */

@Stateless
@TransactionManagement
public class OrderRepository implements OrderInt {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @Override
    public void saveOrUpdate(OrderEntity orderEntity) {
        OrderEntity order = em.find(OrderEntity.class, orderEntity.getId());
        if (order == null) {
            em.persist(orderEntity);
        } else {
            mapOrderDAO(order, orderEntity);
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

    @Override
    public List<OrderEntity> findByName(String title) {
        return em.createQuery("from OrderEntity where comment LIKE ?1", OrderEntity.class)
                .setParameter(1, title).getResultList();
    }

    @Override
    public void delete(OrderEntity orderEntity) {
        OrderEntity order = em.find(OrderEntity.class, orderEntity.getId());
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
