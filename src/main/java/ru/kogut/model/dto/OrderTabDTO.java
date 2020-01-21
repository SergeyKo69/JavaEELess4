package ru.kogut.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kogut.model.dao.OrderDAO;
import ru.kogut.model.dao.ProductDAO;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
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
