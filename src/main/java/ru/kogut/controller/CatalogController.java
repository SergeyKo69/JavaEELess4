package ru.kogut.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.ProductDAO;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.CategoryService;
import ru.kogut.service.ProductService;

import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author S.Kogut on 22.01.2020
 */
@Data
@Named
@ViewScoped
@NoArgsConstructor
public class CatalogController implements Serializable {

    private ProductService productService;
    private CategoryService categoryService;
    private ModelMapper modelMapper;
    private ProductDTO selectedProduct;
    private List<ProductDTO> productList;

    @Inject
    public CatalogController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.modelMapper = new ModelMapper();
        this.selectedProduct = new ProductDTO();
    }

    public void preloadCatalog(ComponentSystemEvent componentSystemEvent) {
        List<ProductDAO> productDAOList = productService.findAll();
        this.productList = new ArrayList<>();
        productDAOList.forEach(p->{
            this.productList.add(modelMapper.map(
                    p, ProductDTO.class
            ));
        });
    }

    public List<ProductDTO> getAllProducts() {
        return this.productList;
    }

    public String getProduct(String id) {
        this.selectedProduct = modelMapper.map(productService.findById(id), ProductDTO.class);
        return "product.xhtml";
    }

}
