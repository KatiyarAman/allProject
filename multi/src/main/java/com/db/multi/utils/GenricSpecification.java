package com.db.multi.utils;

import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GenricSpecification {

    public static <T> Specification<T> getSpecs(Map<String, Object> map) {
        Specification<T> tempSpec = null;
        Specification<T> spec = null;
        for (Map.Entry<String, Object> entrySet : map.entrySet()) {
        	if( !(entrySet.getKey().equalsIgnoreCase("pageNumber"))&& !(entrySet.getKey().equalsIgnoreCase("pageSize"))) {
        		tempSpec= findByColumnName(entrySet.getKey(), entrySet.getValue());
                spec=spec!=null?Specification.where(spec).and(tempSpec):tempSpec;
        	}
           
        }
        return spec;
    }

    private static <T> Specification<T> findByColumnName(String key, Object value) {
        return (rt, qr, cb) -> cb.like(rt.get(key),"%" +value+"%");
    }
    
    private static <T> Specification<T> findByColumn(String key, Object value) {
    	
    	
    	return (root,query, criteriaBuilder)->{
            ListJoin<T,T> phoneJoin = (ListJoin<T, T>) root.join("amam");
            Predicate equalPredicate = criteriaBuilder.equal(phoneJoin.get(key), value);
			return equalPredicate;
    	};

    }
    

}
