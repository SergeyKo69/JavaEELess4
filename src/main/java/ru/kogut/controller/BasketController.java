package ru.kogut.controller;

import lombok.Data;
import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.OrderDAO;
import ru.kogut.model.dao.OrderTabDAO;
import ru.kogut.model.dto.BasketDTO;
import ru.kogut.model.dto.BasketUnitDTO;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.OrderService;
import ru.kogut.service.ProductService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author S.Kogut on 21.01.2020
 */
@Data
@Named
@SessionScoped
public class BasketController implements Serializable {

    private BasketDTO basket;
    private ModelMapper modelMapper;

    @Inject
    private OrderService orderService;

    @Inject
    private ProductService productService;

    public BasketController() {
        this.basket = new BasketDTO();
    }

    public Integer getQuantityPosition() {
        return basket.getBasketList().size();
    }

    public void add(ProductDTO product, Integer quantity) {
        BasketUnitDTO basketUnit = basket.getBasketList()
                .stream()
                .filter(b->b.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);
        if (basketUnit != null) {
            basketUnit.setQuantity(basketUnit.getQuantity().add(new BigDecimal(quantity)));
            basketUnit.setAmount(basketUnit.getQuantity().multiply(basketUnit.getPrice()));
        } else {
            BasketUnitDTO unit = new BasketUnitDTO();
            unit.setId(UUID.randomUUID().toString());
            unit.setProduct(product);
            unit.setPrice(product.getPrice());
            unit.setQuantity(new BigDecimal(quantity));
            unit.setAmount(product.getPrice().multiply(new BigDecimal(quantity)));
            basket.getBasketList().add(unit);
        }
        recount();
    }

    public void remove(BasketUnitDTO unit) {
        if (unit.getQuantity().compareTo(BigDecimal.ONE) == 0) {
            basket.getBasketList().remove(unit);
        } else {
            unit.setQuantity(unit.getQuantity().subtract(BigDecimal.ONE));
            unit.setAmount(unit.getQuantity().multiply(unit.getPrice()));
        }
        recount();
    }

    public void recount() {
        basket.setTotalAmount(BigDecimal.ZERO);
        basket.getBasketList().forEach(u->{
            basket.setTotalAmount(basket.getTotalAmount().add(u.getAmount()));
        });
    }

    public String confirmBuy() {
        OrderDAO order = new OrderDAO();
        order.setNumber(UUID.randomUUID().toString());
        order.setDate(LocalDateTime.now());
        order.setComment("");
        order.setOrderTabList(new ArrayList<>());
        order.setTotalAmount(basket.getTotalAmount());
        for (BasketUnitDTO rowBasket : basket.getBasketList()) {
            OrderTabDAO tab = new OrderTabDAO();
            tab.setOrder(order);
            tab.setProduct(productService.findById(rowBasket.getProduct().getId()));
            tab.setPrice(rowBasket.getPrice());
            tab.setQuantity(rowBasket.getQuantity());
            tab.setAmount(rowBasket.getAmount());
            order.getOrderTabList().add(tab);
        }
        orderService.saveOrUpdate(order);
        basket.getBasketList().clear();
        basket.setTotalAmount(BigDecimal.ZERO);
        return "order.xhtml";
    }
}
