package com.db.multi.service.impl;

import com.db.multi.cms.entity.CmsElement;
import com.db.multi.cms.entity.CmsElementField;
import com.db.multi.cms.repository.CmsElementFieldRepository;
import com.db.multi.cms.repository.CmsElementRepository;
import com.db.multi.request.CmsElementRequest;
import com.db.multi.service.CmsElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CmsElementServiceImpl implements CmsElementService {

    @Autowired
    private CmsElementRepository cmsElementRepository;
    @Autowired
    private CmsElementFieldRepository cmsElementFieldRepository;
    @Override
    public CmsElement createElement(CmsElementRequest cmsElementRequest) {
        CmsElement cmsElement= cmsElementRequest.getCmsElement();

        //save Cms Element
        cmsElementRepository.save(cmsElement);

        List<CmsElementField> cmsElementFieldList=cmsElementRequest.getAttributes()
                .stream().map(it->CmsElementField.mapFromCMSElemnetField(it,cmsElement.getId()))
                .collect(Collectors.toList());

        // save cms element fied value
        cmsElementFieldRepository.saveAll(cmsElementFieldList);

        return cmsElement;
    }

    @Override
    public CmsElement updateElement(CmsElementRequest cmsElementRequest) {
        CmsElement cmsElement= cmsElementRequest.getCmsElement();

        //save Cms Element
        cmsElementRepository.save(cmsElement);

        List<CmsElementField> cmsElementFieldList=cmsElementRequest.getAttributes()
                .stream().map(it->CmsElementField.mapFromCMSElemnetField(it,cmsElement.getId()))
                .collect(Collectors.toList());

        // save cms element fied value
        cmsElementFieldRepository.saveAll(cmsElementFieldList);

        List<Integer> ids= cmsElementFieldList.stream().map(it->it.getId()).collect(Collectors.toList());


        //delete unsaved value
        cmsElementFieldRepository.deleteByElementIdAndIdNotIn(cmsElement.getId(),ids);


        return cmsElement;
    }
}
