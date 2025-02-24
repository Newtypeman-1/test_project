package kr.co.iei.admin.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.admin.model.vo.AdminRowMapper;
import kr.co.iei.doctor.model.vo.Doctor;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private AdminRowMapper adminRowMapper;

	public int insertDoctor(Doctor d) {
		String query = "insert into doctor_tbl values(doctor_seq.nextval,?,?,?,?,?,?,?)";
		Object[] params = {d.getDoctorId(),d.getDoctorPw(),d.getDoctorEmail(),d.getDoctorName(),d.getDoctorPhone(),d.getDoctorImg(),d.getDepartmentNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int deleteDoctor(Doctor d) {
		String query = "delete doctor_tbl where doctor_id = ?";
		Object[] params = {d.getDoctorId()};
		int r = jdbc.update(query, params);
		return r;
	}

}
