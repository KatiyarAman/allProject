package com.db.multi.request;

import com.db.multi.cms.entity.Component;
import com.db.multi.model.AttributeValue;
import lombok.Data;

import java.util.List;
@Data
public class ComponentRequest {
    private Component component;
    private List<AttributeValue> attributes;
}
