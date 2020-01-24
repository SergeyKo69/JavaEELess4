package ru.kogut.repository;

import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */

public interface BaseCRUDRepository<K, T> {

    void saveOrUpdate(T t);

    T findById(K k);

    List<T> findAll();

    List<T> findByName(String title);

    void delete(T t);

}
