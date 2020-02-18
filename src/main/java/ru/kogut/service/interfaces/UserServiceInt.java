package ru.kogut.service.interfaces;

import ru.kogut.model.dto.UserDTO;
import ru.kogut.repository.BaseCRUDRepository;

/**
 * @author S.Kogut on 16.02.2020
 */
public interface UserServiceInt extends BaseCRUDRepository<String, UserDTO> {
}
