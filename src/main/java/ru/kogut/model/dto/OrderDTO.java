package ru.kogut.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kogut.model.dao.OrderTabDAO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */

@Data
@NoArgsConstructor
public class OrderDTO implements Serializable {

    private String number;

    private LocalDateTime date;

    private BigDecimal totalAmount;

    private String comment;

    private List<OrderTabDTO> orderTabList;

}
