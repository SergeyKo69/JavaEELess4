package ru.kogut.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author S.Kogut on 21.01.2020
 */

@Data
@Entity
@Table(name = "orderTab")
@EqualsAndHashCode(callSuper = true)
public class OrderTabEntity extends AbstractEntity {

    @ManyToOne
    private OrderEntity order;

    @ManyToOne
    private ProductEntity product;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "amount")
    private BigDecimal amount;

}
