package ru.kogut.controller;

import lombok.Data;
import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.ProductDAO;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.CategoryService;
import ru.kogut.service.ProductService;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Inject
    private ProductService productService;

    @Inject
    private CategoryService categoryService;

    private ModelMapper modelMapper;

    public ProductController() {
        this.product = new ProductDTO();
        this.modelMapper = new ModelMapper();
    }

    public void preloadProduct(ComponentSystemEvent componentSystemEvent) {
        List<ProductDAO> productDAOList = productService.findAll();
        this.productList = new ArrayList<>();
        productDAOList.forEach(p->{
            this.productList.add(modelMapper.map(p, ProductDTO.class));
        });
    }

    public List<ProductDTO> getAllProduct() {
        return this.productList;
    }

    public String newProduct() {
        this.product = new ProductDTO();
        return "productEdit.xhtml";
    }

    public String editProduct(ProductDTO editedProduct) {
        this.product = editedProduct;
        return "productEdit.xhtml";
    }

    public void removeProduct(ProductDTO removedProduct) {
        productService.delete(modelMapper.map(removedProduct, ProductDAO.class));
    }

    public String save() {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
        ProductDAO productDAO = modelMapper.map(product, ProductDAO.class);
        if (product.getCategory() != null) {
            productDAO.setCategory(categoryService.findById(product.getCategory().getId()));
        }

        productService.saveOrUpdate(productDAO);
        return "productList.xhtml";
    }

}
