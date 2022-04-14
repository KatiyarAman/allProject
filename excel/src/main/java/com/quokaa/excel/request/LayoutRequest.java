package com.quokaa.excel.request;

import com.quokaa.excel.cms.CmsLayout;
import com.quokaa.excel.cms.CmsLayoutComponent;
import com.quokaa.excel.model.AttributeValue;
import lombok.Data;

import java.util.List;
@Data
public class LayoutRequest {
    private CmsLayout cmsLayout;
    private List<AttributeValue> attributes;
    private List<CmsLayoutComponent> components;
}
