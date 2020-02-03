package ru.kogut.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author S.Kogut on 21.01.2020
 */

@Data
@Entity
@Table(name = "category")
@EqualsAndHashCode(callSuper = true)
public class CategoryEntity extends AbstractEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

}
