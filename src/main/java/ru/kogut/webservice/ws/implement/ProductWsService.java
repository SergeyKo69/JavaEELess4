package ru.kogut.webservice.ws.implement;

import ru.kogut.enumerations.RolesEnum;
import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.interfaces.CategoryServiceInt;
import ru.kogut.service.interfaces.ProductServiceInt;
import ru.kogut.webservice.ws.interfaces.IProduct;

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
@WebService(endpointInterface = "ru.kogut.webservice.ws.interfaces.IProduct")
public class ProductWsService implements IProduct {

    @EJB
    private ProductServiceInt productService;

    @EJB
    private CategoryServiceInt categoryService;

    @Override
    public void add(ProductDTO product) {
        productService.saveOrUpdate(product);
    }

    @Override
    public void delete(ProductDTO product) {
        productService.delete(product);
    }

    @Override
    public ProductDTO findById(String id) {
        return productService.findById(id);
    }

    @Override
    public ProductDTO addCategory(String idProduct, String idCategory) {

        CategoryDTO categoryDTO = categoryService.findById(idCategory);
        if (categoryDTO == null) {
            return null;
        }
        ProductDTO productDTO = productService.findById(idProduct);
        productDTO.setCategory(categoryDTO);
        productService.saveOrUpdate(productDTO);

        return productDTO;
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        return productService.findByName(name);
    }

    @Override
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }

    @Override
    public List<ProductDTO> findByIdCategory(String idCategory) {
        return productService.findByCategoryId(idCategory);
    }
}
