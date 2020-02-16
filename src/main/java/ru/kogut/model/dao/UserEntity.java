package ru.kogut.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author S.Kogut on 16.02.2020
 */

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends  AbstractEntity {

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<RoleEntity> roles;

}
