package ru.kogut.model.dao;

import lombok.Data;
import org.intellij.lang.annotations.Identifier;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author S.Kogut on 21.01.2020
 */
@Data
@MappedSuperclass
public class AbstractEntity {

    @Id
    @Identifier
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

}
