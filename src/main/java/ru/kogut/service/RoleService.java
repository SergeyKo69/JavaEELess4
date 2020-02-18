package ru.kogut.service;

import org.modelmapper.ModelMapper;
import ru.kogut.model.dao.RoleEntity;
import ru.kogut.model.dto.RoleDTO;
import ru.kogut.service.interfaces.RoleInt;
import ru.kogut.service.interfaces.RoleServiceInt;

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
public class RoleService implements RoleServiceInt, Serializable {

    @EJB
    private RoleInt roleRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void saveOrUpdate(RoleDTO roleDTO) {
        roleRepository.saveOrUpdate(modelMapper.map(roleDTO,RoleEntity.class));
    }

    @Override
    public RoleDTO findById(String id) {
        return modelMapper.map(roleRepository.findById(id),RoleDTO.class);
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(r->modelMapper.map(r, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDTO> findByName(String name) {
        return roleRepository.findByName(name).stream()
                .map(r->modelMapper.map(r,RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(RoleDTO roleDTO) {
        roleRepository.delete(modelMapper.map(roleDTO, RoleEntity.class));
    }

}
