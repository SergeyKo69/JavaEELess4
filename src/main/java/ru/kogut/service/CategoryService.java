package ru.kogut.service;

import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.CategoryEntity;
import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.repository.CategoryRepository;
import ru.kogut.service.interfaces.CategoryInt;
import ru.kogut.service.interfaces.CategoryServiceInt;

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
public class CategoryService implements CategoryServiceInt, Serializable {

    @EJB
    private CategoryInt categoryRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void saveOrUpdate(CategoryDTO categoryDTO) {
        categoryRepository.saveOrUpdate(modelMapper.map(categoryDTO, CategoryEntity.class));
    }

    @Override
    public CategoryDTO findById(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id);
        if (categoryEntity == null) {
            return null;
        }
         return modelMapper.map(categoryEntity, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(c->modelMapper.map(c,CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findByName(String title) {
        return categoryRepository.findByName(title).stream()
                .map(c->modelMapper.map(c,CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(CategoryDTO categoryDTO) {
        categoryRepository.delete(modelMapper.map(categoryDTO, CategoryEntity.class));
    }
}
