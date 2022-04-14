package com.quokaa.excel.service;

import com.quokaa.excel.request.LayoutRequest;

public interface CmsLayoutService {
    public String createLayout(LayoutRequest layoutRequest);
    public String updateLayout(LayoutRequest layoutRequest);
}
