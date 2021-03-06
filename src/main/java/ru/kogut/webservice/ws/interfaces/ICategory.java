package ru.kogut.webservice.ws.interfaces;

import ru.kogut.model.dto.CategoryDTO;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import java.util.List;

/**
 * @author S.Kogut on 10.02.2020
 */

@WebService
@RolesAllowed("ADMIN")
public interface ICategory {

    @WebMethod
    void add(CategoryDTO category);

    @WebMethod
    List<CategoryDTO> findAll();

    @WebMethod
    CategoryDTO update(CategoryDTO category);

    @WebMethod
    void delete(CategoryDTO category);

}
