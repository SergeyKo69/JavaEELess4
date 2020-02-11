package ru.kogut.webservice.rest;

import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.interfaces.CategoryServiceInt;
import ru.kogut.service.interfaces.ProductServiceInt;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author S.Kogut on 11.02.2020
 */

@Path("product")
public class ProductRS {
    @EJB
    private ProductServiceInt productService;

    @EJB
    private CategoryServiceInt categoryService;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ProductDTO add(ProductDTO product) {
        if (product == null || product.getId().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        productService.saveOrUpdate(product);
        return product;
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(ProductDTO product) {
        if (product.getId().isEmpty() && productService.findById(product.getId()) == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        productService.delete(product);
    }

    @PUT
    @Path("/addCategory/{productId}/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_HTML)
    public ProductDTO addCategory(@PathParam("productId") String productId,
                                  @PathParam("categoryId") String categoryId) {
        if (productId.isEmpty() || categoryId.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        ProductDTO product = productService.findById(productId);
        if (product == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        CategoryDTO category = categoryService.findById(categoryId);
        if (category == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        product.setCategory(category);
        productService.saveOrUpdate(product);
        return product;
    }

    @GET
    @Path("/findById/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO findById(@PathParam("id") String id) {
        if (id.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return productService.findById(id);
    }

    @GET
    @Path("/findByName/{name}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> findByName(@PathParam("name") String name) {
        if (name.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return productService.findByName(name);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> findByAll() {
        return productService.findAll();
    }

    @GET
    @Path("/findByCategoryId/{categoryId}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> findByCategoryId(@PathParam("categoryId") String categoryId) {
        if (categoryId.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return productService.findByCategoryId(categoryId);
    }
}
