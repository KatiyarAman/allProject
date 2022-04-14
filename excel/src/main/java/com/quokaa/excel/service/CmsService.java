package com.quokaa.excel.service;

import com.quokaa.excel.request.CmsRequest;

public interface CmsService {
    public  String createComponent(CmsRequest cmsRequest);
    public  String updateComponet(CmsRequest cmsRequest);
    public String updateProductDetails(Integer compoentId);
}
