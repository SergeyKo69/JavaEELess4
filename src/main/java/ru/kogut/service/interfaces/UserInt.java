package ru.kogut.service.interfaces;

import ru.kogut.model.dao.UserEntity;
import ru.kogut.repository.BaseCRUDRepository;

import javax.ejb.Local;

/**
 * @author S.Kogut on 16.02.2020
 */

@Local
public interface UserInt extends BaseCRUDRepository<String, UserEntity> {
}
