package com.db.multi.csv.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.db.multi.csv.entity.PlaFeed;

@Component
public class PlaFeedRepo {

	private static final Logger logger=LogManager.getLogger(PlaFeedRepo.class);
//	@Autowired
//	private PlaFeedRepository plaFeedRepository;

	@Autowired
	@Qualifier("csvJdbcTemplate")
	private JdbcTemplate csvJdbcTemplate;

	public List<PlaFeed> getById(Integer id) {
		String sql = "select * from pla_feed where id in (:ids)";
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(csvJdbcTemplate);
		Map<String, List<Integer>> map = new HashMap<>();
		List<Integer> integers = new ArrayList<Integer>();
		integers.add(9992);
		integers.add(9993);
		integers.add(9994);
		integers.add(9995);

		map.put("ids", integers);

		return namedParameterJdbcTemplate.query(sql, map,
				(rs, num) -> new PlaFeed(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12)));
	}

	public PlaFeed insert(PlaFeed plaFeed) {
		String sql = "insert into pla_feed (`id`,`availability`,`custom_label2`,`custom_label3`,`custom_label4`,`gtin`,`identifier_exist`,`is_criteo_active`,`is_facebook_active`,`is_google_active`,`msn`,`promotion_id`)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?))";
		boolean inserted = csvJdbcTemplate.execute(sql, (PreparedStatementCallback<Boolean>) ps -> {
			ps.setLong(1, plaFeed.getId());
			ps.setString(2, plaFeed.getAvailability());
			ps.setString(3, plaFeed.getCustomLabel2());
			ps.setString(4, plaFeed.getCustomLabel3());
			ps.setString(5, plaFeed.getCustomLabel4());
			ps.setString(6, plaFeed.getGtin());
			ps.setString(7, plaFeed.getIdentifierExists());
			ps.setString(8, plaFeed.getIsFacebookActive());
			ps.setString(9, plaFeed.getIsGoogleActive());
			ps.setString(10, plaFeed.getMsn());
			ps.setString(11, plaFeed.getPromotionId());
			return ps.execute();
		});
		if (inserted)
			return plaFeed;

		return new PlaFeed();
	}

//    public List<PlaFeed> list(Specification<PlaFeed> pSpecification,Pageable pageable){
//    	return plaFeedRepository.findAll(pSpecification,pageable);
//    }
//    
//    public PlaFeed getOne(Long id) {
//    	return plaFeedRepository.getById(id);
//    }

	public List<PlaFeed> list() {
		String sql = "select * from pla_feed";
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				csvJdbcTemplate.getDataSource());

		return namedParameterJdbcTemplate.query(sql,
				(rs, num) -> new PlaFeed(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12)));

	}

	public List<PlaFeed> findbyColumn(StringBuffer queryParams) {
		
		String sql= "Select * from pla_feed "+queryParams;
		logger.info("Finale sql query "+sql);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(csvJdbcTemplate.getDataSource());
		return namedParameterJdbcTemplate.query(sql,(rs,num)->new PlaFeed(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
				rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
				rs.getString(10), rs.getString(11), rs.getString(12)));
	}

}
