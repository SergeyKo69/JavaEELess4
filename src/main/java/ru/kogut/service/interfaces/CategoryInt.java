package ru.kogut.service.interfaces;

import ru.kogut.model.dao.CategoryEntity;
import ru.kogut.repository.BaseCRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * @author S.Kogut on 03.02.2020
 */

@Local
public interface CategoryInt extends BaseCRUDRepository<String, CategoryEntity> {
}
