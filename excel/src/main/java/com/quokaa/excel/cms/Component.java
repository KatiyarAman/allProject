package com.quokaa.excel.cms;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "component")
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "component_name")
    private String componentName;

    @Column(name = "component_type")
    private String componentType;

    @Column(name = "parent_structure")
    private Boolean isParentStructure;

    @Column(name = "child_component_id")
    private Integer childComponentId;

    private Boolean status;

}
