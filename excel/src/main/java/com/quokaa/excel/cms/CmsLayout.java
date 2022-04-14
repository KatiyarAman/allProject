package com.quokaa.excel.cms;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cms_layout")
public class CmsLayout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "layout_name")
    private String layoutName;

    @Column(name = "layout_code")
    private String layoutCode;

    @Column(name = "parent_structure")
    private Boolean isParentStructure;

    private Boolean status;

    private String device;
}
