package ru.kogut.service.interfaces;

import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.repository.BaseCRUDRepository;

/**
 * @author S.Kogut on 09.02.2020
 */
public interface CategoryServiceInt extends BaseCRUDRepository<String, CategoryDTO> {
}
