package ru.kogut.webservice.ws.implement;

import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.service.interfaces.CategoryServiceInt;
import ru.kogut.webservice.ws.interfaces.ICategory;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

/**
 * @author S.Kogut on 10.02.2020
 */

@WebService(endpointInterface = "ru.kogut.webservice.ws.interfaces.ICategory")
public class CategoryWsService implements ICategory {

    @EJB
    private CategoryServiceInt categoryService;

    @Override
    public void add(CategoryDTO category) {
        categoryService.saveOrUpdate(category);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @Override
    public CategoryDTO update(CategoryDTO category) {
        categoryService.saveOrUpdate(category);
        return category;
    }

    @Override
    public void delete(CategoryDTO category) {
        categoryService.delete(category);
    }
}
