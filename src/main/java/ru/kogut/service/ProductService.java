package ru.kogut.service;

import ru.kogut.model.dao.ProductDAO;
import ru.kogut.repository.BaseCRUDRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */

@Named
@Transactional
@ApplicationScoped
public class ProductService implements BaseCRUDRepository<String, ProductDAO>, Serializable {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @Override
    public void saveOrUpdate(ProductDAO productDAO) {
        ProductDAO product = em.find(ProductDAO.class, productDAO.getId());
        if (product == null) {
            em.persist(productDAO);
        } else {
            mapProductDAO(product, productDAO);
            em.merge(product);
        }
    }

    @Override
    public ProductDAO findById(String id) {
       return em.find(ProductDAO.class, id);
    }

    @Override
    public List<ProductDAO> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductDAO> query  = cb.createQuery(ProductDAO.class);
        Root<ProductDAO> c = query.from(ProductDAO.class);
        query.select(c);
        TypedQuery<ProductDAO> q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<ProductDAO> findByName(String title) {
        return em.createNamedQuery("product.findByName", ProductDAO.class)
                .setParameter("title", title).getResultList();
    }

    @Override
    public void delete(ProductDAO productDAO) {
        ProductDAO product = em.find(ProductDAO.class, productDAO.getId());
        if (product != null) {
            em.remove(product);
        }
    }

    public void mapProductDAO(ProductDAO in, ProductDAO out) {
        in.setTitle(out.getTitle());
        in.setCategory(out.getCategory());
        in.setShortDescription(out.getShortDescription());
        in.setFullDescription(out.getFullDescription());
        in.setPrice(out.getPrice());
        in.setPathTitlePicture(out.getPathTitlePicture());
    }
}
