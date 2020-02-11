package ru.kogut.service;

import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.ProductEntity;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.interfaces.ProductInt;
import ru.kogut.service.interfaces.ProductServiceInt;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author S.Kogut on 21.01.2020
 */

@Stateless
@TransactionAttribute
public class ProductService implements ProductServiceInt,Serializable {

    @EJB
    private ProductInt productRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void saveOrUpdate(ProductDTO productDTO) {
        productRepository.saveOrUpdate(modelMapper.map(productDTO, ProductEntity.class));
    }

    @Override
    public ProductDTO findById(String s) {
        ProductEntity product = productRepository.findById(s);
        if (product == null) return null;
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(p-> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByName(String title) {
        return productRepository.findByName(title).stream()
                .map(p-> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ProductDTO productDTO) {
        productRepository.delete(modelMapper.map(productDTO, ProductEntity.class));
    }

    @Override
    public List<ProductDTO> findByCategoryId(String id) {
        return productRepository.findByCategoryId(id).stream()
                .map(c->modelMapper.map(c,ProductDTO.class))
                .collect(Collectors.toList());
    }
}
