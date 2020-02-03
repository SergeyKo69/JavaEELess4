package ru.kogut.service.interfaces;

import ru.kogut.model.dao.OrderEntity;
import ru.kogut.repository.BaseCRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * @author S.Kogut on 03.02.2020
 */

@Local
@Stateless
public interface OrderInt extends BaseCRUDRepository<String, OrderEntity> {
}
