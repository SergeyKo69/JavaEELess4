package ru.kogut.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */

@Data
public class BasketDTO implements Serializable {

    private List<BasketUnitDTO> basketList;

    private BigDecimal totalAmount;

    public BasketDTO() {
        this.basketList = new ArrayList<>();
        this.totalAmount = BigDecimal.ZERO;
    }
}
