package ru.kogut.repository;

import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */

public interface BaseCRUDRepository<K, T> {

    T saveOrUpdate(T t);

    T findById(K k);

    List<T> findAll();

    void delete(T t);

}
