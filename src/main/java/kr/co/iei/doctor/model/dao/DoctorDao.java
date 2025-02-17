package kr.co.iei.doctor.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.doctor.model.vo.DoctorRowMapper;

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
}
