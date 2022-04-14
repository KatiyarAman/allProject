package com.db.multi.request;

import com.db.multi.cms.entity.CmsElement;
import com.db.multi.model.AttributeValue;
import lombok.Data;

import java.util.List;
@Data
public class CmsElementRequest {
    private CmsElement cmsElement;
    private List<AttributeValue> attributes;


}
