package ru.kogut.repository;

import javax.ejb.Local;
import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */

@Local
public interface BaseCRUDRepository<K, T> {

    void saveOrUpdate(T t);

    T findById(K k);

    List<T> findAll();

    List<T> findByName(String title);

    void delete(T t);

}
