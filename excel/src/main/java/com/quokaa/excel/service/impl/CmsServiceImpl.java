package com.quokaa.excel.service.impl;

import com.quokaa.excel.cms.Component;
import com.quokaa.excel.cms.ComponentValue;
import com.quokaa.excel.cms.repository.ComponentRepo;
import com.quokaa.excel.cms.repository.ComponentValueRepo;
import com.quokaa.excel.request.CmsRequest;
import com.quokaa.excel.service.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CmsServiceImpl  implements CmsService {

    @Autowired
    private ComponentRepo componentRepo;

    @Autowired
    private ComponentValueRepo componentValueRepo;

    @Override
    @Transactional(transactionManager = "cmsTxn")
    public String createComponent(CmsRequest cmsRequest) {

        if(cmsRequest.getComponent()==null)
            return "badRequest";

        Component component= cmsRequest.getComponent();
        componentRepo.save(component);

        //save component value

        if(cmsRequest.getAttributes()!=null){
            List<ComponentValue> componentValue=cmsRequest.getAttributes()
                    .stream().map(obj->ComponentValue.mapFromCmsRequest(obj,component.getId()))
                    .collect(Collectors.toList());
            //save component value
            componentValueRepo.saveAll(componentValue);
        }

        return "success";
    }

    @Override
    public String updateComponet(CmsRequest cmsRequest) {
        if(cmsRequest.getComponent()==null)
            return "badRequest";

        Component component= cmsRequest.getComponent();
        //save component
        componentRepo.save(component);

        if(cmsRequest.getAttributes()!=null){
            List<ComponentValue> componentValue=cmsRequest.getAttributes()
                    .stream().map(obj->ComponentValue.mapFromCmsRequest(obj,component.getId()))
                    .collect(Collectors.toList());
            //save component value
            componentValueRepo.saveAll(componentValue);

            //delet unsaved componentvalue
         List<Integer> savedIds= componentValue.stream().map(it->it.getId()).collect(Collectors.toList());

         if(savedIds.isEmpty())
             componentValueRepo.deleteByComponentId(component.getId());

         componentValueRepo.deleteByComponentIdAndIdNotIn(component.getId(),savedIds);
        }

        return "updated successfully";
    }

    @Override
    public String updateProductDetails(Integer compoentId) {
        return null;
    }
}
