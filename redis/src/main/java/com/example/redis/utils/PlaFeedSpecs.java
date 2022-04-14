package com.example.redis.utils;

import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class PlaFeedSpecs {

    public static <T>Specification<T> getSpecs(Map<String,String> map){
        Specification<T> tempSpec=null;
        Specification<T>spec=null;
        for(Map.Entry<String,String> entrySet:map.entrySet()){
            if(!(entrySet.getKey().equalsIgnoreCase("pageNumber")) && !(entrySet.getKey().equalsIgnoreCase("pageSize"))){
                tempSpec=findByColumnName(entrySet.getKey(),entrySet.getValue());
                spec=spec!=null?Specification.where(spec).and(tempSpec):tempSpec;
            }
        }
       return spec;
    }

    public static  <T> Specification<T> findByColumnName(String columnName,Object value){
        return (rt,q,cb)-> cb.equal(rt.get(columnName),value);
    }
}
