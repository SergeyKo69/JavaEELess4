package ru.kogut.service.interfaces;

import ru.kogut.model.dto.OrderDTO;
import ru.kogut.repository.BaseCRUDRepository;

import javax.ejb.Local;

/**
 * @author S.Kogut on 09.02.2020
 */

public interface OrderServiceInt extends BaseCRUDRepository<String, OrderDTO> {
}
