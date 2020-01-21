package ru.kogut.service;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import ru.kogut.configuration.ConfigHbn;
import ru.kogut.model.dao.CategoryDAO;
import ru.kogut.repository.BaseCRUDRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */
public class CategoryService implements BaseCRUDRepository<String, CategoryDAO> {

    @Inject
    private ConfigHbn configHbn;

    @Override
    public CategoryDAO saveOrUpdate(CategoryDAO categoryDAO) {
        Session session = configHbn.getSession();
        session.beginTransaction();
        session.save(categoryDAO);
        session.getTransaction().commit();
        return categoryDAO;
    }

    @Override
    public CategoryDAO findById(String id) {
        Session session = configHbn.getSession();
        CategoryDAO categoryDAO;
        try {
            categoryDAO = session.load(CategoryDAO.class, id);
            categoryDAO.getTitle();
        } catch (ObjectNotFoundException e) {
            return null;
        }
        return categoryDAO;
    }

    @Override
    public List<CategoryDAO> findAll() {
        Session session = configHbn.getSession();
        return session.createQuery("FROM CategoryDAO ", CategoryDAO.class).list();
    }

    @Override
    public void delete(CategoryDAO categoryDAO) {
        Session session = configHbn.getSession();
        session.beginTransaction();
        session.delete(categoryDAO);
        session.getTransaction().commit();
    }
}
