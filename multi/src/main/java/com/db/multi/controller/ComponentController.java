package com.db.multi.controller;

import com.db.multi.cms.entity.Component;
import com.db.multi.request.ComponentRequest;
import com.db.multi.service.ComponentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;
@RestController
@RequestMapping("/api/component/*")
public class ComponentController {

    @Autowired
    ComponentService componentService;

    @PostMapping("createComponent")
    public Component createComponent( @RequestBody ComponentRequest componentRequest )  {
        return componentService.createComponent(componentRequest);
    }
}
