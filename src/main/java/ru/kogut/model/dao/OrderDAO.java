package ru.kogut.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author S.Kogut on 21.01.2020
 */

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderDAO extends AbstractDAO {

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "totlaAmount")
    private BigDecimal totalAmount;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderTabDAO> orderTabList;

}
