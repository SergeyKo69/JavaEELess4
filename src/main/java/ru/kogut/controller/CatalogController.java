package ru.kogut.controller;

import lombok.Data;
import org.modelmapper.ModelMapper;
import ru.kogut.log.Logger;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.interfaces.CategoryServiceInt;
import ru.kogut.service.interfaces.ProductServiceInt;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.List;

/**
 * @author S.Kogut on 22.01.2020
 */
@Data
@Named
@SessionScoped
public class CatalogController implements Serializable {

    @EJB
    private ProductServiceInt productService;

    @EJB
    private CategoryServiceInt categoryService;

    private ModelMapper modelMapper;
    private ProductDTO selectedProduct;
    private List<ProductDTO> productList;

    public CatalogController() {
        this.modelMapper = new ModelMapper();
        this.selectedProduct = new ProductDTO();
    }

    @Interceptors({Logger.class})
    public void preloadCatalog(ComponentSystemEvent componentSystemEvent) {
        this.productList = productService.findAll();
    }

    @Interceptors({Logger.class})
    public List<ProductDTO> getAllProducts() {
        return this.productList;
    }

    @Interceptors({Logger.class})
    public String getProduct(String id) {
        this.selectedProduct = modelMapper.map(productService.findById(id), ProductDTO.class);
        return "product.xhtml";
    }

}
