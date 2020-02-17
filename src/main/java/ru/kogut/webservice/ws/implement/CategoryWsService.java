package ru.kogut.webservice.ws.implement;

import ru.kogut.enumerations.RolesEnum;
import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.service.interfaces.CategoryServiceInt;
import ru.kogut.webservice.ws.interfaces.ICategory;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import java.util.List;

/**
 * @author S.Kogut on 10.02.2020
 */
@RolesAllowed("ADMIN")
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
