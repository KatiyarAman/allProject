package com.db.multi.service.impl;

import com.db.multi.cms.entity.Component;
import com.db.multi.cms.entity.ComponentValue;
import com.db.multi.cms.repository.ComponentRepository;
import com.db.multi.cms.repository.ComponentValueRepo;
import com.db.multi.model.EmailRequest;
import com.db.multi.request.ComponentRequest;
import com.db.multi.service.ComponentService;
import com.db.multi.utils.ApiManager;
import com.db.multi.utils.GenricSpecification;
import com.db.multi.utils.WebEngageMail;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComponentServiceImpl implements ComponentService {
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    ComponentValueRepo componentValueRepo;

    @Autowired
    ApiManager apiManager;

    @Autowired
    WebEngageMail engageMail;

    @Override
    public Component createComponent(ComponentRequest componentRequest) {

        Component component= componentRequest.getComponent();

        //save component
        componentRepository.save(component);

        List<ComponentValue> componentValues=componentRequest.getAttributes()
                .stream().map(it->ComponentValue.mapFromComponentValue(it,component.getId()))
                .collect(Collectors.toList());

        //save component value
        componentValueRepo.saveAll(componentValues);



//        JSONObject token= new JSONObject();
//        token.put("component_name",component.getComponentName());
//        token.put("child_component_id",component.getChildComponentId());
//        token.put("status",component.getStatus());
//        EmailRequest emailRequest= new EmailRequest("12",30,token,"aman.katiyar@moglix.com");
//       String response= engageMail.sentMail(emailRequest);
//        System.out.println("******************"  +response);
//
//        JSONObject categoryObject = new JSONObject();
//        categoryObject.put("id", null);
//        categoryObject.put("categoryName", "DemmoCategory");
//        String ApiUrl = "http://localhost:8082/catalog/category/createCategory";
//        String responseCategory = apiManager.postRequest(ApiUrl, categoryObject);
        //  Component component1 = componentRepository.save(component);
        return component;
    }

    @Override
    public Component updateComponent(ComponentRequest componentRequest) {

        Component component= componentRequest.getComponent();

        //save component
        componentRepository.save(component);

        List<ComponentValue> componentValues=componentRequest.getAttributes()
                .stream().map(it->ComponentValue.mapFromComponentValue(it,component.getId()))
                .collect(Collectors.toList());

        List<Integer> ids= componentValues.stream().map(it->it.getId()).collect(Collectors.toList());

        //delete the record which is containing the componentID
        componentValueRepo.deleteByComponentIdAndIdNotIn(component.getId(),ids);

        return component;
    }

    @Override
    public Component getComponentById(Integer componentId) {
        return componentRepository.getById(componentId);
    }

    @Override
    public List<Component> list(Map<String, Object> param, Integer pageNumber, Integer pageSize) {
        Specification<Component> componentSpecification= GenricSpecification.getSpecs(param);
        return componentRepository.findAll(componentSpecification);
    }
}
