package ru.kogut.service.interfaces;

import ru.kogut.model.dao.ProductEntity;
import ru.kogut.repository.BaseCRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author S.Kogut on 03.02.2020
 */
@Local
public interface ProductInt extends BaseCRUDRepository<String, ProductEntity> {

    List<ProductEntity> findByCategoryId(String id);

}
