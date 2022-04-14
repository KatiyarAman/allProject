package com.quokaa.excel.service.impl;

import com.quokaa.excel.cms.CmsLayout;
import com.quokaa.excel.cms.CmsLayoutComponent;
import com.quokaa.excel.cms.CmsLayoutValue;
import com.quokaa.excel.cms.repository.CmsLayoutComponentRepo;
import com.quokaa.excel.cms.repository.CmsLayoutRepo;
import com.quokaa.excel.cms.repository.CmsLayoutValueRepo;
import com.quokaa.excel.request.LayoutRequest;
import com.quokaa.excel.service.CmsLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CmsLayoutSeviceImpl implements CmsLayoutService {

    @Autowired
    private CmsLayoutRepo cmsLayoutRepo;

    @Autowired
    private CmsLayoutValueRepo cmsLayoutValueRepo;

    @Autowired
    private CmsLayoutComponentRepo cmsLayoutComponentRepo;

    @Override
    public String createLayout(LayoutRequest layoutRequest) {
        if(layoutRequest.getCmsLayout()==null)
            return  null;
        CmsLayout cmsLayout=layoutRequest.getCmsLayout();

        //save cmsLayout
        cmsLayoutRepo.save(cmsLayout);

        if(layoutRequest.getAttributes()!=null){

            List<CmsLayoutValue> cmsLayoutValues=layoutRequest.getAttributes()
                    .stream().map(it->CmsLayoutValue.mapFromLayoutRequest(it,cmsLayout.getId()))
                    .collect(Collectors.toList());

            //save cms LayoutValue
            cmsLayoutValueRepo.saveAll(cmsLayoutValues);
        }

        if(layoutRequest.getComponents()!=null){
            List<CmsLayoutComponent> cmsLayoutComponents= layoutRequest.getComponents()
                    .stream().map(it->CmsLayoutComponent.mapFromLayoutRequest(it,cmsLayout.getId()))
                    .collect(Collectors.toList());

            cmsLayoutComponentRepo.saveAll(cmsLayoutComponents);
        }
        return "success";
    }

    @Override
    public String updateLayout(LayoutRequest layoutRequest) {
        if(layoutRequest.getCmsLayout()==null)
            return  null;
        CmsLayout cmsLayout=layoutRequest.getCmsLayout();

        //save cmsLayout
        cmsLayoutRepo.save(cmsLayout);

        if(layoutRequest.getAttributes()!=null){

            List<CmsLayoutValue> cmsLayoutValues=layoutRequest.getAttributes()
                    .stream().map(it->CmsLayoutValue.mapFromLayoutRequest(it,cmsLayout.getId()))
                    .collect(Collectors.toList());

            //save cms LayoutValue
            cmsLayoutValueRepo.saveAll(cmsLayoutValues);

            List<Integer> ids=cmsLayoutValues.stream().map(it->it.getId()).collect(Collectors.toList());

            if(ids.isEmpty())
                cmsLayoutValueRepo.deleteByLayoutId(cmsLayout.getId());

            //delete unsaved value
            cmsLayoutValueRepo.deleteByLayoutIdAndIdNotIn(cmsLayout.getId(),ids);
        }

        if(layoutRequest.getComponents()!=null){
            List<CmsLayoutComponent> cmsLayoutComponents= layoutRequest.getComponents()
                    .stream().map(it->CmsLayoutComponent.mapFromLayoutRequest(it,cmsLayout.getId()))
                    .collect(Collectors.toList());

            cmsLayoutComponentRepo.saveAll(cmsLayoutComponents);

            List<Integer> ids=cmsLayoutComponents.stream().map(it->it.getId()).collect(Collectors.toList());

            cmsLayoutComponentRepo.deleteByLayoutIdAndIdNotIn(cmsLayout.getId(),ids);
        }
        return "success";
    }
}
