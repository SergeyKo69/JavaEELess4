package ru.kogut.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.kogut.enumerations.RolesEnum;

import javax.persistence.*;
import java.util.List;

/**
 * @author S.Kogut on 16.02.2020
 */

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private RolesEnum name;

    @ManyToMany
    private List<UserEntity> users;

}
