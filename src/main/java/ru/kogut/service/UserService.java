package ru.kogut.service;

import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.UserEntity;
import ru.kogut.model.dto.UserDTO;
import ru.kogut.service.interfaces.UserInt;
import ru.kogut.service.interfaces.UserServiceInt;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author S.Kogut on 16.02.2020
 */

@Stateless
@TransactionAttribute
public class UserService implements UserServiceInt, Serializable {

    @EJB
    private UserInt userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void saveOrUpdate(UserDTO userDTO) {
        userRepository.saveOrUpdate(modelMapper.map(userDTO, UserEntity.class));
    }

    @Override
    public UserDTO findById(String id) {
        return modelMapper.map(userRepository.findById(id), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(u->modelMapper.map(u,UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByName(String name) {
        return userRepository.findByName(name).stream()
                .map(u->modelMapper.map(u,UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UserDTO userDTO) {
        userRepository.delete(modelMapper.map(userDTO, UserEntity.class));
    }

}
