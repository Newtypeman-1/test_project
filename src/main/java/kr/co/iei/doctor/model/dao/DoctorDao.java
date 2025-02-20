package kr.co.iei.doctor.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.doctor.model.vo.DoctorRowMapper;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.review.model.vo.Review;

@Repository
public class DoctorDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private DoctorRowMapper doctorRowMapper;
	
	public Doctor selectOneDoctor(Doctor d) {
		String query = "select * from doctor_tbl where doctor_id = ? and doctor_pw = ?";
		Object[] params = {d.getDoctorId(), d.getDoctorPw()};
		List list = jdbc.query(query, doctorRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			Doctor doctor = (Doctor)list.get(0);
			return doctor;
		}
	}
	public int updateDoctor(Doctor d) {
		String query = "update doctor_tbl set doctor_pw = ?, doctor_phone = ?, doctor_email = ? where doctor_no = ?";
		Object[] params = {d.getDoctorPw(), d.getDoctorPhone(), d.getDoctorEmail(), d.getDoctorNo()};
		int result = jdbc.update(query, params);
		return result;
	}
	public List selectAllDoctor() {
		String query = "select * from doctor_tbl order by 1";
		List list2 = jdbc.query(query,doctorRowMapper);
		return list2;
	}
	
	public Doctor findId(Doctor d) {
		String query = "select * from doctor_tbl where doctor_email=?";
		Object[] params = {d.getDoctorEmail()};
		List list = jdbc.query(query, doctorRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}
		return (Doctor)list.get(0);
	}
	
	public Doctor findPw(Doctor d) {
		String query = "select * from doctor_tbl where doctor_id=? and doctor_email=?";
		Object[] params = {d.getDoctorId(), d.getDoctorEmail()};
		List list = jdbc.query(query, doctorRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}
		return (Doctor)list.get(0);
	}
	
}