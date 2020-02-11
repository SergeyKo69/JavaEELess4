package ru.kogut.service;

import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.OrderEntity;
import ru.kogut.model.dto.OrderDTO;
import ru.kogut.service.interfaces.OrderInt;
import ru.kogut.service.interfaces.OrderServiceInt;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author S.Kogut on 21.01.2020
 */

@Stateless
@TransactionAttribute
public class OrderService implements OrderServiceInt, Serializable {

    @EJB
    private OrderInt orderRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void saveOrUpdate(OrderDTO orderDTO) {
        orderRepository.saveOrUpdate(modelMapper.map(orderDTO, OrderEntity.class));
    }

    @Override
    public OrderDTO findById(String id) {
        return modelMapper.map(orderRepository.findById(id), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(o->modelMapper.map(o, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByName(String title) {
        return orderRepository.findByName(title).stream()
                .map(o->modelMapper.map(o,OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(OrderDTO orderDTO) {
        orderRepository.delete(modelMapper.map(orderDTO,OrderEntity.class));
    }
}
