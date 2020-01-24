package ru.kogut.converter;

import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.CategoryDAO;
import ru.kogut.model.dto.CategoryDTO;
import ru.kogut.service.CategoryService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author S.Kogut on 22.01.2020
 */

@Named
@ApplicationScoped
public class Converter {

    @Inject
    private CategoryService categoryService;

    public javax.faces.convert.Converter getCategoryConverter(){

        return new javax.faces.convert.Converter() {

            @Override
            public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String submittedValue) {
                if (submittedValue == null || submittedValue.isEmpty()) {
                    return null;
                }
                try {
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(categoryService.findById(submittedValue), CategoryDTO.class);
                } catch (NumberFormatException e) {
                    throw new ConverterException(new FacesMessage(submittedValue + " is not a valid category ID"), e);
                }
            }

            @Override
            public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object modelValue) {
                if (modelValue == null) {
                    return "";
                }
                if (modelValue instanceof CategoryDTO) {
                    return ((CategoryDTO) modelValue).getId();
                } else {
                    throw new ConverterException(new FacesMessage(modelValue + " is not a valid category"));
                }
            }
        };
    }
}
