package kr.co.iei.review.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewRowMapper2 implements RowMapper<Review>{

	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review r = new Review();
		r.setReviewContent(rs.getString("review_content"));
		r.setReviewDate(rs.getString("review_date"));
		r.setReviewNo(rs.getInt("review_no"));
		r.setReviewTitle(rs.getString("review_title"));
		r.setReviewWriter(rs.getString("review_writer"));
		r.setDoctorNo(rs.getInt("doctor_no"));
		r.setReviewStar(rs.getInt("review_star"));
		r.setTreatmentNo(rs.getInt("treatment_no"));
		return r;
	}
}