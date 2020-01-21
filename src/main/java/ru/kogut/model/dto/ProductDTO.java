package ru.kogut.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author S.Kogut on 21.01.2020
 */

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private CategoryDTO category;

    private String title;

    private String shortDescription;

    private String fullDescription;

    private BigDecimal price;

    private String pathTitlePicture;

    @Override
    public String toString() {
        return title;
    }

}
