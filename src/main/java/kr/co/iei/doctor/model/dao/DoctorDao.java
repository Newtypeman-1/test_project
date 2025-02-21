package kr.co.iei.doctor.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.doctor.model.vo.DoctorRowMapper;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.treat.model.vo.TreatRowMapper3;

@Repository
public class DoctorDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private DoctorRowMapper doctorRowMapper;
	@Autowired
	private TreatRowMapper3 treatRowMapper3;
	
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
	public List allMedicalRecords(Doctor doctor, int start, int end) {
		String query = "select * from (select rownum as rnum, z.* from (select * from treatment_tbl t join doctor_tbl d on t.doctor_no = d.doctor_no join member_tbl m on t.member_no = m.member_no join department_tbl p on d.department_no = p.department_no where d.doctor_no = ? order by 1 desc) z) where rnum between ? and ?";
		Object[] params = {doctor.getDoctorNo(), start, end};
		List list = jdbc.query(query, treatRowMapper3, params);
		return list;
	}
	public int memberTotalCount(Doctor doctor) {
		String query = "select count(*) from treatment_tbl where doctor_no = ?";
		Object[] params = {doctor.getDoctorNo()};
		int r = jdbc.queryForObject(query, Integer.class, params);
		return r;
	}
	
}