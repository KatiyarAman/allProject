package com.db.multi.service;

import com.db.multi.cms.entity.Component;
import com.db.multi.controller.ComponentController;
import com.db.multi.request.ComponentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface ComponentService
{ public Component createComponent(ComponentRequest componentRequest);
    public Component updateComponent(ComponentRequest componentRequest);
    public Component getComponentById(Integer componentId);
    public List<Component> list(Map<String,Object> param, Integer pageNumber, Integer pageSize);
}
