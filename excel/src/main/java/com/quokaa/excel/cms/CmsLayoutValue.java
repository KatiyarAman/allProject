package com.quokaa.excel.cms;

import com.quokaa.excel.model.AttributeValue;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cms_layout_value")
public class CmsLayoutValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "layout_id")
    private Integer layoutId;

    @Column(name = "attribute_key_id")
    private Integer attributeKeyId;

    @Column(name = "attribute_value")
    private String attributeValue;

    public static CmsLayoutValue mapFromLayoutRequest(AttributeValue it, Integer layoutId) {
        CmsLayoutValue cmsLayoutValue= new CmsLayoutValue();
        cmsLayoutValue.setId(it.getId());
        cmsLayoutValue.setAttributeKeyId(it.getAttributeKeyId());
        cmsLayoutValue.setAttributeValue(it.getAttributeValue());
        cmsLayoutValue.setLayoutId(layoutId);
        return cmsLayoutValue;
    }
}
