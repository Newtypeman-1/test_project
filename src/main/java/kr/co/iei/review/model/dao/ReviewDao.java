package kr.co.iei.review.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.review.model.vo.ReviewRowMapper;

@Repository
public class ReviewDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ReviewRowMapper reviewRowMapper;
	
	public List reviewAllList(int start, int end) {
		String query = "select * from (select rownum as rnum, r.* from (select * from review v join doctor_tbl d on (v.doctor_no = d.doctor_no) order by review_no desc)r) where rnum between ? and ?";
		Object[] params = {start, end};
		List list = jdbc.query(query, reviewRowMapper, params);
		return list;
	}

	public int totalReview() {
		String query = "select count(*) from review";
		int totalReview = jdbc.queryForObject(query, Integer.class);
		return totalReview;
	}
	
}
