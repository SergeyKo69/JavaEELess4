package ru.kogut.controller;

import lombok.Data;
import org.modelmapper.ModelMapper;
import ru.kogut.log.Logger;
import ru.kogut.model.dao.CategoryEntity;
import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.service.CategoryService;
import ru.kogut.service.interfaces.CategoryInt;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
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
public class CategoryController implements Serializable {

    private CategoryDTO category;
    private List<CategoryDTO> categoryList;

    @EJB
    private CategoryInt categoryService;
    private ModelMapper modelMapper;

    @Interceptors({Logger.class})
    public void preloadCategory(ComponentSystemEvent componentSystemEvent) {
        List<CategoryEntity> categoryDAOList = categoryService.findAll();
        this.categoryList = new ArrayList<>();
        categoryDAOList.forEach(c->{
            this.categoryList.add(modelMapper.map(c, CategoryDTO.class));
        });
    }

    public CategoryController() {
        this.modelMapper = new ModelMapper();
        this.category = new CategoryDTO();
    }

    @Interceptors({Logger.class})
    public List<CategoryDTO> getAllCategory() {
        return this.categoryList;
    }

    @Interceptors({Logger.class})
    public String newCategory() {
        category = new CategoryDTO();
        return "categoryEdit.xhtml";
    }

    @Interceptors({Logger.class})
    public String editCategory(CategoryDTO editedCategory) {
        this.category = editedCategory;
        return "categoryEdit.xhtml";
    }

    @Interceptors({Logger.class})
    public void removeCategory(CategoryDTO removedCategoty) {
        categoryService.delete(modelMapper.map(removedCategoty, CategoryEntity.class));
    }

    @Interceptors({Logger.class})
    public String save() {
        if (category.getId() == null || category.getId().isEmpty()) {
            category.setId(UUID.randomUUID().toString());
        }
        categoryService.saveOrUpdate(modelMapper.map(category, CategoryEntity.class));
        return "categoryList.xhtml";
    }

}
