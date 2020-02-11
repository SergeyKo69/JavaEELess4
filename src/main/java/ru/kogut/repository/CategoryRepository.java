package ru.kogut.repository;

import ru.kogut.model.dao.CategoryEntity;
import ru.kogut.service.interfaces.CategoryInt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
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
@TransactionAttribute
public class CategoryRepository implements CategoryInt {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @Override
    public void saveOrUpdate(CategoryEntity categoryEntity) {
        CategoryEntity category = em.find(CategoryEntity.class, categoryEntity.getId());
        if (category == null) {
            em.persist(categoryEntity);
        } else {
            mapCategoryDAO(category, categoryEntity);
            em.merge(category);
        }
    }

    @Override
    public CategoryEntity findById(String id) {
        return em.find(CategoryEntity.class, id);
    }

    @Override
    public List<CategoryEntity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> query  = cb.createQuery(CategoryEntity.class);
        Root<CategoryEntity> c = query.from(CategoryEntity.class);
        query.select(c);
        TypedQuery<CategoryEntity> q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<CategoryEntity> findByName(String title) {
        return em.createQuery("from CategoryEntity where title like ?1", CategoryEntity.class)
                .setParameter(1, title).getResultList();
    }

    @Override
    public void delete(CategoryEntity categoryEntity) {
        CategoryEntity category = em.find(CategoryEntity.class, categoryEntity.getId());
        if (category != null) {
            em.remove(category);
        }
    }

    public void mapCategoryDAO(CategoryEntity in, CategoryEntity out) {
        in.setTitle(out.getTitle());
        in.setDescription(out.getDescription());
    }
}
