package com.db.multi.controller;

import com.db.multi.cms.entity.CmsElement;
import com.db.multi.request.CmsElementRequest;
import com.db.multi.service.CmsElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cmsElement/*")
public class CmsElementController {

    @Autowired
    private CmsElementService cmsElementService;

    @PostMapping(value = "createElement")
    public CmsElement createElement(@RequestBody CmsElementRequest cmsElementRequest) {
        return cmsElementService.createElement(cmsElementRequest);
    }
}
