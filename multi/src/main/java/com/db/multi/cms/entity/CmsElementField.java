package com.db.multi.cms.entity;
import com.db.multi.model.AttributeValue;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "cms_element_field")
public class CmsElementField {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "element_id")
    private Integer elementId;

    @Column(name = "attribute_key_id")
    private Integer attributeKeyId;

    @Column(name = "attribute_value")
    private String attributeValue;

    public static CmsElementField mapFromCMSElemnetField(AttributeValue it, Integer id) {
        CmsElementField cmsElementField= new CmsElementField();
        cmsElementField.setElementId(id);
        cmsElementField.setId(it.getId());
        cmsElementField.setAttributeKeyId(it.getAttributeKeyId());
        cmsElementField.setAttributeValue(it.getAttributeValue());
        return  cmsElementField;
    }
}
