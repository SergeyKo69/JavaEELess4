package ru.kogut.controller;

import lombok.Data;
import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.OrderDAO;
import ru.kogut.model.dao.ProductDAO;
import ru.kogut.model.dto.OrderDTO;
import ru.kogut.model.dto.ProductDTO;
import ru.kogut.service.OrderService;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    @Inject
    private OrderService orderService;

    public void preloadOrder(ComponentSystemEvent componentSystemEvent) {
        List<OrderDAO> orderDAOList = orderService.findAll();
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

    public List<OrderDTO> getAllOrders() {
        return this.orderList;
    }
}
