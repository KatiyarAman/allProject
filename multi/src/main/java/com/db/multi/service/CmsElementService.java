package com.db.multi.service;

import com.db.multi.cms.entity.CmsElement;
import com.db.multi.request.CmsElementRequest;

public interface CmsElementService {

    public CmsElement createElement(CmsElementRequest cmsElementRequest);
    public CmsElement updateElement(CmsElementRequest cmsElementRequest);
}
