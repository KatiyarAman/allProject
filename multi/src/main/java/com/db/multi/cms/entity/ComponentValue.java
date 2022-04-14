package com.db.multi.cms.entity;

import com.db.multi.model.AttributeValue;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "component_value")
public class ComponentValue {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "attribute_key_id")
    private Integer attributeKeyId;

    @Column(name = "attribute_value")
    private String attributeValue;

    public static ComponentValue mapFromComponentValue(AttributeValue attributeValue, Integer componentId) {
        ComponentValue componentValue= new ComponentValue();
        System.out.println("********AttributeValue "+attributeValue.getAttributeKeyId() + " "+attributeValue.getAttributeValue());
        componentValue.setComponentId(componentId);
        componentValue.setId(attributeValue.getId());
        componentValue.setAttributeKeyId(attributeValue.getAttributeKeyId());
        componentValue.setAttributeValue(attributeValue.getAttributeValue());
        return componentValue;
    }
}
