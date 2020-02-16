package ru.kogut.repository;

import ru.kogut.model.dao.UserEntity;
import ru.kogut.service.interfaces.UserInt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author S.Kogut on 16.02.2020
 */

@Stateless
@TransactionAttribute
public class UserRepository implements UserInt {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @Override
    public void saveOrUpdate(UserEntity userEntity) {
        UserEntity user = em.find(UserEntity.class, userEntity.getId());
        if (user == null) {
            em.persist(userEntity);
        } else {
            mapUserDAO(user, userEntity);
            em.merge(user);
        }
    }

    @Override
    public UserEntity findById(String id) {
        return em.find(UserEntity.class, id);
    }

    @Override
    public List<UserEntity> findAll() {
        return em.createQuery("FROM UserEntity ", UserEntity.class).getResultList();
    }

    @Override
    public List<UserEntity> findByName(String name) {
        return em.createQuery("from UserEntity where login like ?1", UserEntity.class)
                .setParameter(1, name).getResultList();
    }

    @Override
    public void delete(UserEntity userEntity) {
        UserEntity user = em.find(UserEntity.class, userEntity.getId());
        if (user != null) {
            em.remove(user);
        }
    }

    public void mapUserDAO(UserEntity in, UserEntity out) {
        in.setLogin(out.getLogin());
        in.setPassword(out.getPassword());
        in.setRoles(out.getRoles());
    }
}
