package ru.kogut.repository;

import ru.kogut.model.dao.ProductEntity;
import ru.kogut.service.interfaces.ProductInt;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author S.Kogut on 09.02.2020
 */
@Stateless
@TransactionManagement
public class ProductRepository implements ProductInt {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @Override
    public void saveOrUpdate(ProductEntity productEntity) {
        ProductEntity product = em.find(ProductEntity.class, productEntity.getId());
        if (product == null) {
            em.persist(productEntity);
        } else {
            mapProductDAO(product, productEntity);
            em.merge(product);
        }
    }

    @Override
    public ProductEntity findById(String id) {
        return em.find(ProductEntity.class, id);
    }

    @Override
    public List<ProductEntity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query  = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> c = query.from(ProductEntity.class);
        query.select(c);
        TypedQuery<ProductEntity> q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<ProductEntity> findByName(String title) {
        return em.createQuery("from ProductEntity where title LIKE ?1", ProductEntity.class)
                .setParameter(1, title).getResultList();
    }

    @Override
    public void delete(ProductEntity productEntity) {
        ProductEntity product = em.find(ProductEntity.class, productEntity.getId());
        if (product != null) {
            em.remove(product);
        }
    }

    public void mapProductDAO(ProductEntity in, ProductEntity out) {
        in.setTitle(out.getTitle());
        in.setCategory(out.getCategory());
        in.setShortDescription(out.getShortDescription());
        in.setFullDescription(out.getFullDescription());
        in.setPrice(out.getPrice());
        in.setPathTitlePicture(out.getPathTitlePicture());
    }

    @Override
    public List<ProductEntity> findByCategoryId(String id) {
        return em.createQuery("from ProductEntity where category.id = ?1", ProductEntity.class)
                .setParameter(1,id).getResultList();
    }
}
