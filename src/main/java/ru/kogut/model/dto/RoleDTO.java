package ru.kogut.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kogut.enumerations.RolesEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @author S.Kogut on 16.02.2020
 */

@Data
@NoArgsConstructor
public class RoleDTO  implements Serializable {

    private String id;

    private RolesEnum name;
}
