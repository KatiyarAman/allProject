package com.db.multi.cms.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "component")
public class Component {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "component_name")
    @NotEmpty
    private String componentName;

    @Column(name = "component_type")
    @NotEmpty
    //@NotBlank(message = "component type can not be null")
    private String componentType;

    @Column(name = "parent_structure")
    private Boolean isParentStructure;

    @Column(name = "child_component_id")
    @NotNull
    private Integer childComponentId;

    private Boolean status;

    public Component() {

    }
}
