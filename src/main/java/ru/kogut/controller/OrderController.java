package ru.kogut.controller;

import lombok.Data;
import ru.kogut.log.Logger;
import ru.kogut.model.dto.OrderDTO;
import ru.kogut.service.interfaces.OrderServiceInt;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.List;

/**
 * @author S.Kogut on 23.01.2020
 */

@Data
@Named
@SessionScoped
public class OrderController implements Serializable {

    private List<OrderDTO> orderList;

    @EJB
    private OrderServiceInt orderService;

    @Interceptors({Logger.class})
    public void preloadOrder(ComponentSystemEvent componentSystemEvent) {
        this.orderList = orderService.findAll();
    }

    @Interceptors({Logger.class})
    public List<OrderDTO> getAllOrders() {
        return this.orderList;
    }
}
