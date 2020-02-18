package ru.kogut.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author S.Kogut on 16.02.2020
 */

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private String id;

    private String login;

    private String password;

    private List<RoleDTO> roles;

    @Override
    public String toString() {
        return  id + " " + login;
    }
}
