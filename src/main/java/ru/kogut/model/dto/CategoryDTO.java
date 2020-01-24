package ru.kogut.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author S.Kogut on 21.01.2020
 */

@Data
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    private String id;

    private String title;

    private String description;

    @Override
    public String toString() {
        return id;
    }
}
