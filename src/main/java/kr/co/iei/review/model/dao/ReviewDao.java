package kr.co.iei.review.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.vo.Member;
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

	public List allReview(Doctor doctor) {
		String query = "select * from (select rownum as rnum, h.* from(select r.*,(select doctor_name from doctor_tbl where doctor_no = r.doctor_no) doctor_name from review r where doctor_no = ? order by 1 desc)h) where rnum between 1 and 5";
		Object[] params = {doctor.getDoctorNo()};
		List allReview = jdbc.query(query, reviewRowMapper, params);
		return allReview;
	}

	public List memberAllReview(Member member) {
		String query = "select * from (select rownum as rnum, e.* from (select * from review r where review_writer = ? order by review_no desc)e) where rnum between 1 and 5";
		Object[] params = {member.getMemberId()};
		List memberAllReview = jdbc.query(query, reviewRowMapper);
		return memberAllReview;
	}
	
}
