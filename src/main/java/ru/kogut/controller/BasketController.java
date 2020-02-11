package ru.kogut.controller;

import lombok.Data;
import org.modelmapper.ModelMapper;
import ru.kogut.log.Logger;
import ru.kogut.model.dao.OrderEntity;
import ru.kogut.model.dao.OrderTabEntity;
import ru.kogut.model.dao.ProductEntity;
import ru.kogut.model.dto.BasketDTO;
import ru.kogut.model.dto.BasketUnitDTO;
import ru.kogut.model.dto.OrderDTO;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.interfaces.OrderServiceInt;
import ru.kogut.service.interfaces.ProductServiceInt;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;
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

    @EJB
    private OrderServiceInt orderService;

    @EJB
    private ProductServiceInt productService;

    public BasketController() {
        this.basket = new BasketDTO();
        this.modelMapper = new ModelMapper();
    }

    @Interceptors({Logger.class})
    public Integer getQuantityPosition() {
        return basket.getBasketList().size();
    }

    @Interceptors({Logger.class})
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

    @Interceptors({Logger.class})
    public void remove(BasketUnitDTO unit) {
        if (unit.getQuantity().compareTo(BigDecimal.ONE) == 0) {
            basket.getBasketList().remove(unit);
        } else {
            unit.setQuantity(unit.getQuantity().subtract(BigDecimal.ONE));
            unit.setAmount(unit.getQuantity().multiply(unit.getPrice()));
        }
        recount();
    }

    @Interceptors({Logger.class})
    public void recount() {
        basket.setTotalAmount(BigDecimal.ZERO);
        basket.getBasketList().forEach(u->{
            basket.setTotalAmount(basket.getTotalAmount().add(u.getAmount()));
        });
    }

    @Interceptors({Logger.class})
    public String confirmBuy() {
        OrderEntity order = new OrderEntity();
        order.setNumber(UUID.randomUUID().toString());
        order.setDate(LocalDateTime.now());
        order.setComment("");
        order.setOrderTabList(new ArrayList<>());
        order.setTotalAmount(basket.getTotalAmount());
        for (BasketUnitDTO rowBasket : basket.getBasketList()) {
            OrderTabEntity tab = new OrderTabEntity();
            tab.setOrder(order);
            tab.setProduct(modelMapper.map(productService.findById(rowBasket.getProduct().getId()), ProductEntity.class));
            tab.setPrice(rowBasket.getPrice());
            tab.setQuantity(rowBasket.getQuantity());
            tab.setAmount(rowBasket.getAmount());
            order.getOrderTabList().add(tab);
        }
        orderService.saveOrUpdate(modelMapper.map(order, OrderDTO.class));
        basket.getBasketList().clear();
        basket.setTotalAmount(BigDecimal.ZERO);
        return "order.xhtml";
    }
}
