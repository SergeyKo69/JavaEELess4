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
public class OrderTabDTO implements Serializable {

    private ProductDTO product;

    private BigDecimal quantity;

    private BigDecimal price;

    private BigDecimal amount;

}
