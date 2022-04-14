package com.quokaa.excel.request;

import com.quokaa.excel.cms.Component;
import com.quokaa.excel.model.AttributeValue;
import lombok.Data;

import java.util.List;
@Data
public class CmsRequest {
    private Component component;
    private List<AttributeValue> attributes;
}
