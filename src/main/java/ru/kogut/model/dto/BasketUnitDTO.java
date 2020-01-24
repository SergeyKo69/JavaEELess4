package ru.kogut.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author S.Kogut on 21.01.2020
 */

@Data
@NoArgsConstructor
public class BasketUnitDTO implements Serializable {

    private String id = UUID.randomUUID().toString();

    private ProductDTO product;

    private BigDecimal quantity;

    private BigDecimal price;

    private BigDecimal amount;

}
