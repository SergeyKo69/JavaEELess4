package ru.kogut.service;

import ru.kogut.model.dao.CategoryDAO;
import ru.kogut.model.dao.ProductDAO;
import ru.kogut.repository.BaseCRUDRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
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
public class CategoryService implements BaseCRUDRepository<String, CategoryDAO>, Serializable {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @Override
    public void saveOrUpdate(CategoryDAO categoryDAO) {
        CategoryDAO category = em.find(CategoryDAO.class, categoryDAO.getId());
        if (category == null) {
            em.persist(categoryDAO);
        } else {
            mapCategoryDAO(category, categoryDAO);
            em.merge(category);
        }
    }

    @Override
    public CategoryDAO findById(String id) {
        return em.find(CategoryDAO.class, id);
    }

    @Override
    public List<CategoryDAO> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CategoryDAO> query  = cb.createQuery(CategoryDAO.class);
        Root<CategoryDAO> c = query.from(CategoryDAO.class);
        query.select(c);
        TypedQuery<CategoryDAO> q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<CategoryDAO> findByName(String title) {
        return em.createQuery("from CategoryDAO where title like ?1", CategoryDAO.class)
                .setParameter(1, title).getResultList();
    }

    @Override
    public void delete(CategoryDAO categoryDAO) {
        CategoryDAO category = em.find(CategoryDAO.class, categoryDAO.getId());
        if (category != null) {
            em.remove(category);
        }
    }

    public void mapCategoryDAO(CategoryDAO in, CategoryDAO out) {
        in.setTitle(out.getTitle());
        in.setDescription(out.getDescription());
    }

}
