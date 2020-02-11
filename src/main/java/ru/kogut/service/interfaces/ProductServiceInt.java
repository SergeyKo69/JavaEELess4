package ru.kogut.service.interfaces;

import ru.kogut.model.dto.ProductDTO;
import ru.kogut.repository.BaseCRUDRepository;

import java.util.List;

/**
 * @author S.Kogut on 09.02.2020
 */
public interface ProductServiceInt extends BaseCRUDRepository<String, ProductDTO> {

    List<ProductDTO> findByCategoryId(String id);
}
