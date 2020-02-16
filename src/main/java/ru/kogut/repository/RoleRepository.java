package ru.kogut.repository;

import ru.kogut.model.dao.RoleEntity;
import ru.kogut.service.interfaces.RoleInt;

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
public class RoleRepository implements RoleInt {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @Override
    public void saveOrUpdate(RoleEntity roleEntity) {
        RoleEntity role = em.find(RoleEntity.class, roleEntity.getId());
        if (role == null) {
            em.persist(roleEntity);
        } else {
            mapRoleDAO(role, roleEntity);
            em.merge(role);
        }
    }

    @Override
    public RoleEntity findById(String id) {
        return em.find(RoleEntity.class, id);
    }

    @Override
    public List<RoleEntity> findAll() {
        return em.createQuery("FROM RoleEntity ", RoleEntity.class).getResultList();
    }

    @Override
    public List<RoleEntity> findByName(String name) {
        return em.createQuery("from RoleEntity where name = ?1", RoleEntity.class)
                .setParameter(1, name).getResultList();
    }

    @Override
    public void delete(RoleEntity roleEntity) {
        RoleEntity role = em.find(RoleEntity.class, roleEntity.getId());
        if (role != null) {
            em.remove(role);
        }
    }

    public void mapRoleDAO(RoleEntity in, RoleEntity out) {
        in.setName(out.getName());
        in.setUsers(out.getUsers());
    }
}
