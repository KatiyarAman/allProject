package com.quokaa.excel.cms;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cms_layout_component")
public class CmsLayoutComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "layout_id")
    private Integer layoutId;

    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "[order]")
    private Integer order;

    private Boolean status;

    public static CmsLayoutComponent mapFromLayoutRequest(CmsLayoutComponent it, Integer id) {
        CmsLayoutComponent cmsLayoutComponent= it;
        cmsLayoutComponent.setLayoutId(id);
        return cmsLayoutComponent;
    }
}
