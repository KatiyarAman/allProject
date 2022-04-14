package com.db.multi.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class AttributeValue {
    private Integer id;

    private Integer attributeKeyId;

    private String attributeValue;
}
