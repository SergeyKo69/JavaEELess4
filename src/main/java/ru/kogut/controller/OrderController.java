package ru.kogut.controller;

import lombok.Data;
import ru.kogut.log.Logger;
import ru.kogut.model.dao.OrderEntity;
import ru.kogut.model.dto.OrderDTO;
import ru.kogut.service.OrderService;
import ru.kogut.service.interfaces.OrderInt;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.ArrayList;
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
    private OrderInt orderService;

    @Interceptors({Logger.class})
    public void preloadOrder(ComponentSystemEvent componentSystemEvent) {
        List<OrderEntity> orderDAOList = orderService.findAll();
        this.orderList = new ArrayList<>();
        orderDAOList.forEach(o->{
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(o.getId());
            orderDTO.setDate(o.getDate());
            orderDTO.setNumber(o.getNumber());
            orderDTO.setComment(o.getComment());
            orderDTO.setTotalAmount(o.getTotalAmount());
            this.orderList.add(orderDTO);
        });
    }

    @Interceptors({Logger.class})
    public List<OrderDTO> getAllOrders() {
        return this.orderList;
    }
}
