package com.quokaa.excel.cms;

import com.quokaa.excel.model.AttributeValue;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "component_value")
public class ComponentValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "attribute_key_id")
    private Integer attributeKeyId;

    @Column(name = "attribute_value")
    private String attributeValue;

    public static ComponentValue mapFromCmsRequest(AttributeValue obj, Integer id) {
        ComponentValue componentValue= new ComponentValue();
        componentValue.setComponentId(obj.getId());
        componentValue.setAttributeKeyId(obj.getAttributeKeyId());
        componentValue.setAttributeValue(obj.getAttributeValue());
        componentValue.setComponentId(id);
        return componentValue;
    }
}
