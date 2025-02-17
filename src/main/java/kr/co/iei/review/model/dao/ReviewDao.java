package kr.co.iei.review.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDao {
	@Autowired
	private JdbcTemplate jdbc;
	
}
