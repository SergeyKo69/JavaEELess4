package ru.kogut.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author S.Kogut on 21.01.2020
 */

@Data
@Entity
@Table(name = "product")
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends AbstractEntity {

    @ManyToOne
    private CategoryEntity category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "shortDescription")
    private String shortDescription;

    @Column(name = "fullDescription", length = 1000)
    private String fullDescription;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "pathTitlePicture")
    private String pathTitlePicture;

}
