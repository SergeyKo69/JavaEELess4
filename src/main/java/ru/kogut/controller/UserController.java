package ru.kogut.controller;

import lombok.Data;
import ru.kogut.enumerations.RolesEnum;
import ru.kogut.model.dao.RoleEntity;
import ru.kogut.model.dao.UserEntity;
import ru.kogut.model.dto.UserDTO;
import ru.kogut.service.interfaces.UserInt;
import ru.kogut.service.interfaces.UserServiceInt;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author S.Kogut on 16.02.2020
 */

@Data
@Named
@SessionScoped
public class UserController implements Serializable {

    private String login;
    private String password;
    private RolesEnum role;
    private UserDTO userDTO;

    @EJB
    private UserInt userRepository;

    @EJB
    private UserServiceInt userService;

    public String save() {
        UserEntity user = new UserEntity();
        user.setLogin(login);
        user.setPassword(password.toCharArray());
        RoleEntity role = new RoleEntity(Stream.of(user).collect(Collectors.toList()),RolesEnum.USER);
        user.setRoles(Stream.of(role).collect(Collectors.toList()));
        userRepository.saveOrUpdate(user);
        return "login.xhtml";
    }

    public String createNew() {
        UserEntity user = null;
        List<UserEntity> userList = userRepository.findByName(userDTO.getLogin());
        if (userList == null || userList.size() == 0) {
            user = new UserEntity();
        } else {
            user = userList.get(0);
        }
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword().toCharArray());
        RoleEntity role = new RoleEntity(Stream.of(user).collect(Collectors.toList()),getRole());
        user.setRoles(Stream.of(role).collect(Collectors.toList()));
        userRepository.saveOrUpdate(user);
        return "userList.xhtml";
    }

    public List<UserDTO> all() {
        return userService.findAll();
    }

    public String editUser(UserDTO user) {
        userDTO = user;
        return "userEdit.xhtml";
    }

    public String newUser() {
        userDTO = new UserDTO();
        return "userEdit.xhtml";
    }

    public String delete(UserDTO user) {
        userService.delete(user);
        return "userList.xhtml";
    }

    public List<RolesEnum> getRoles() {
        return Stream.of(RolesEnum.values()).collect(Collectors.toList());
    }

}
