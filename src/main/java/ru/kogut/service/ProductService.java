package ru.kogut.service;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import ru.kogut.configuration.ConfigHbn;
import ru.kogut.model.dao.ProductDAO;
import ru.kogut.repository.BaseCRUDRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */
public class ProductService implements BaseCRUDRepository<String, ProductDAO> {

    @Inject
    private ConfigHbn configHbn;

    @Override
    public ProductDAO saveOrUpdate(ProductDAO productDAO) {
        Session session = configHbn.getSession();
        session.beginTransaction();
        session.save(productDAO);
        session.getTransaction().commit();
        return productDAO;
    }

    @Override
    public ProductDAO findById(String id) {
        Session session = configHbn.getSession();
        ProductDAO productDAO;
        try {
            productDAO = session.load(ProductDAO.class, id);
            productDAO.getTitle();
        } catch (ObjectNotFoundException e) {
            return null;
        }
        return productDAO;
    }

    @Override
    public List<ProductDAO> findAll() {
        Session session = configHbn.getSession();
        return session.createQuery("FROM ProductDAO ", ProductDAO.class).list();
    }

    @Override
    public void delete(ProductDAO productDAO) {
        Session session = configHbn.getSession();
        session.beginTransaction();
        session.delete(productDAO);
        session.getTransaction().commit();
    }

}
