package ru.kogut.webservice.rest;

import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.service.interfaces.CategoryServiceInt;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author S.Kogut on 11.02.2020
 */

@Path("category")
public class CategoryRS {

    @EJB
    private CategoryServiceInt categoryService;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryDTO add(CategoryDTO category) {
        if (category == null || category.getId().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        categoryService.saveOrUpdate(category);
        return category;
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryDTO update(CategoryDTO category) {
        if (category == null || category.getId().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        categoryService.saveOrUpdate(category);
        return category;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDTO> all() {
        return categoryService.findAll();
    }

    @DELETE
    @Path("/dalete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(CategoryDTO category) {
        if (category.getId().isEmpty() && categoryService.findById(category.getId()) == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        categoryService.delete(category);
    }
}
