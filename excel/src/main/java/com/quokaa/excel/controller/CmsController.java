package com.quokaa.excel.controller;

import com.quokaa.excel.request.CmsRequest;
import com.quokaa.excel.service.CmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/*")
public class CmsController {

    private static final Logger logger = LoggerFactory.getLogger(CmsController.class);
    @Autowired
    private CmsService cmsService;

    @PostMapping("createCms")
    public String createComponent(@RequestBody CmsRequest cmsRequest) {
        return cmsService.createComponent(cmsRequest);
    }
    @PostMapping("updateCms")
    public String updateComponent(@RequestBody CmsRequest cmsRequest) {
        return cmsService.updateComponet(cmsRequest);
    }
}
