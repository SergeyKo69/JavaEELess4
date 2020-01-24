package ru.kogut.controller;

import lombok.Data;
import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.CategoryDAO;
import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.service.CategoryService;

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
public class CategoryController implements Serializable {

    private CategoryDTO category;
    private List<CategoryDTO> categoryList;

    @Inject
    private CategoryService categoryService;
    private ModelMapper modelMapper;

    public void preloadCategory(ComponentSystemEvent componentSystemEvent) {
        List<CategoryDAO> categoryDAOList = categoryService.findAll();
        this.categoryList = new ArrayList<>();
        categoryDAOList.forEach(c->{
            this.categoryList.add(modelMapper.map(c, CategoryDTO.class));
        });
    }

    public CategoryController() {
        this.modelMapper = new ModelMapper();
        this.category = new CategoryDTO();
    }

    public List<CategoryDTO> getAllCategory() {
        return this.categoryList;
    }

    public String newCategory() {
        category = new CategoryDTO();
        return "categoryEdit.xhtml";
    }

    public String editCategory(CategoryDTO editedCategory) {
        this.category = editedCategory;
        return "categoryEdit.xhtml";
    }

    public void removeCategory(CategoryDTO removedCategoty) {
        categoryService.delete(modelMapper.map(removedCategoty, CategoryDAO.class));
    }

    public String save() {
        if (category.getId() == null || category.getId().isEmpty()) {
            category.setId(UUID.randomUUID().toString());
        }
        categoryService.saveOrUpdate(modelMapper.map(category,CategoryDAO.class));
        return "categoryList.xhtml";
    }

}
