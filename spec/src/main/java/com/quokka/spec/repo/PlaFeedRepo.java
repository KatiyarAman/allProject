package com.quokka.spec.repo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.quokka.spec.model.PlaFeed;

@Component
public class PlaFeedRepo {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<PlaFeed> list(Map<String,Object> param,Integer pageNumer,Integer pageSize){
		
		StringBuffer queryParam= getParam(param,pageNumer,pageSize);
String sql ="SELECT `id`,`msn`,`gtin`,`identifier_exists`,`custom_label2`,`custom_label3`,`custom_label4`,`promotion_id`,`is_google_active`,`is_facebook_active`,`is_criteo_active`,`availability` FROM `pla_feed` WHERE " +queryParam;
		
		System.out.println("final query : " +sql );
		NamedParameterJdbcTemplate namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(jdbcTemplate);
	
		//create constructor inside the model
		return namedParameterJdbcTemplate.query(sql,(rs,num)->new PlaFeed(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
				rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
				rs.getString(10), rs.getString(11), rs.getString(12)));
	}
	
	


	private StringBuffer getParam(Map<String,Object> param,Integer pageNumber,Integer pageSize) {
		
		//lets create dynamic query
		
		StringBuffer query= new StringBuffer();
		
		int count =0;
		for(Map.Entry<String, Object> entryMap:param.entrySet()) {
			
			if(count==0) {
				query.append( entryMap.getKey()).append(" LIKE ").append(" '%").append(entryMap.getValue()).append( "%'");
			}
			else if(count<param.size())
			{
				query.append(" AND ").append( entryMap.getKey()).append(" LIKE ").append(" '%").append(entryMap.getValue()).append("%' ");
			}
			count++;
		}
		//lets use offset
		//pagination Start
				Integer offSet=(pageNumber-1)*pageSize;
				
				query.append(" LIMIT ").append(pageSize).append(" OFFSET ").append(offSet).append(" ;");
		
		
		return query;
	}

}
