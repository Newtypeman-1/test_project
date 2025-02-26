package kr.co.iei.doctor.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.doctor.model.vo.DoctorRowMapper;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.treat.model.vo.Treat;
import kr.co.iei.treat.model.vo.TreatRowMapper;
import kr.co.iei.treat.model.vo.TreatRowMapper3;

@Repository
public class DoctorDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private DoctorRowMapper doctorRowMapper;
	@Autowired
	private TreatRowMapper treatRowMapper;
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
	public List selectAllDoctor(int start, int end) {
		String query = "select * from (select rownum as rnum ,m.* from (select * from doctor_tbl order by 1 desc) m) where rnum between ? and ?";
		Object[] params = {start, end};
		List list2 = jdbc.query(query,doctorRowMapper, params);
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
	
	public int memberSelectCount(Doctor doctor) {
		String query = "select count(*) from treatment_tbl where doctor_no = ?";
		Object[] params = {doctor.getDoctorNo()};
		int r = jdbc.queryForObject(query, Integer.class, params);
		return r;
	}
	
	public Treat selectOpinion(int treatmentNo, int doctorNo) {
		String query = "select * from treatment_tbl t join doctor_tbl d on t.doctor_no = d.doctor_no join member_tbl m on t.member_no = m.member_no join department_tbl p on d.department_no = p.department_no where t.treatment_no = ?";
		Object[] params = {treatmentNo};
		List list = jdbc.query(query, treatRowMapper3, params);
		return (Treat)list.get(0);
	}
	
	public int updateOpinion(Treat t, Doctor doctor) {
		String query = "update treatment_tbl set opinion_symptom = ?, opinion_decision = ?, is_done = 1 where treatment_no = ?";
		Object[] params = {t.getOpinionSymptom(), t.getOpinionDecision(), t.getTreatmentNo()};
		int r = jdbc.update(query, params);
		return r;
	}
	public int doctorTotalCount() {
		String query = "select count(*) from doctor_tbl";
		int r = jdbc.queryForObject(query, Integer.class);
		return r;
	}
	public Doctor idCheck(Doctor d) {
		String query = "select * from doctor_tbl where doctor_id = ?";
		Object[] params = {d.getDoctorId()};
		List list = jdbc.query(query, doctorRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			return (Doctor)list.get(0);
		}
	}
	
}