package kr.co.iei.doctor.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorRatingRowMapper implements RowMapper<Doctor>{

	@Override
	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor d = new Doctor();
		d.setDepartmentNo(rs.getInt("department_no"));
		d.setDoctorEmail(rs.getString("doctor_email"));
		d.setDoctorId(rs.getString("doctor_id"));
		d.setDoctorImg(rs.getString("doctor_img"));
		d.setDoctorName(rs.getString("doctor_name"));
		d.setDoctorNo(rs.getInt("doctor_no"));
		d.setDoctorPhone(rs.getString("doctor_phone"));
		d.setDoctorPw(rs.getString("doctor_pw"));
		d.setDepartmentName(rs.getString("department_no"));
		d.setReviewTotal(rs.getInt("review_total"));
		d.setReviewRating(rs.getInt("review_rating"));
		return d;
	}
}