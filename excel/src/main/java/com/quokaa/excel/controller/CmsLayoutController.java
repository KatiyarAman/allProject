package com.quokaa.excel.controller;

import com.quokaa.excel.request.LayoutRequest;
import com.quokaa.excel.service.CmsLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cms/layout/*")
public class CmsLayoutController {

    @Autowired
    private CmsLayoutService cmsLayoutService;

    @PostMapping("createLayout")
    public String createLayout(@RequestBody LayoutRequest layoutRequest) {
        return cmsLayoutService.createLayout(layoutRequest);
    }

    @PostMapping("updateLayout")
    public String updateLayout(@RequestBody LayoutRequest layoutRequest) {
        return cmsLayoutService.updateLayout(layoutRequest);
    }
}
