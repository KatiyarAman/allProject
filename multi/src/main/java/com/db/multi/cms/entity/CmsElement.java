package com.db.multi.cms.entity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cms_element")
public class CmsElement {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "[order]")
    private Integer order;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
