package ru.kogut.controller;

import lombok.Data;
import org.modelmapper.ModelMapper;
import ru.kogut.log.Logger;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.interfaces.CategoryInt;
import ru.kogut.service.interfaces.ProductServiceInt;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author S.Kogut on 22.01.2020
 */

@Data
@Named
@SessionScoped
public class ProductController implements Serializable {

    private ProductDTO product;
    private List<ProductDTO> productList;

    @EJB
    private ProductServiceInt productService;

    @EJB
    private CategoryInt categoryService;

    private ModelMapper modelMapper;

    public ProductController() {
        this.product = new ProductDTO();
        this.modelMapper = new ModelMapper();
    }

    @Interceptors({Logger.class})
    public void preloadProduct(ComponentSystemEvent componentSystemEvent) {
        this.productList = productService.findAll();
    }

    @Interceptors({Logger.class})
    public List<ProductDTO> getAllProduct() {
        return this.productList;
    }

    @Interceptors({Logger.class})
    public String newProduct() {
        this.product = new ProductDTO();
        return "productEdit.xhtml";
    }

    @Interceptors({Logger.class})
    public String editProduct(ProductDTO editedProduct) {
        this.product = editedProduct;
        return "productEdit.xhtml";
    }

    @Interceptors({Logger.class})
    public void removeProduct(ProductDTO removedProduct) {
        productService.delete(removedProduct);
//        productService.delete(modelMapper.map(removedProduct, ProductEntity.class));
    }

    @Interceptors({Logger.class})
    public String save() {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
//        ProductEntity productDAO = modelMapper.map(product, ProductEntity.class);
//        if (product.getCategory() != null) {
//            productDAO.setCategory(categoryService.findById(product.getCategory().getId()));
//        }

        productService.saveOrUpdate(product);
        return "productList.xhtml";
    }

}
