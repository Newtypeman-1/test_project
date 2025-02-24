package kr.co.iei.treat.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.doctor.model.vo.DoctorRatingRowMapper;
import kr.co.iei.doctor.model.vo.DoctorRowMapper;
import kr.co.iei.doctor.model.vo.DoctorScheduleRowMapper;
import kr.co.iei.treat.model.vo.Department;
import kr.co.iei.treat.model.vo.DepartmentRowMapper;
import kr.co.iei.treat.model.vo.Treat;
import kr.co.iei.treat.model.vo.TreatRowMapper;

@Repository
public class TreatDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private TreatRowMapper treatRowMapper;
	@Autowired
	private DoctorRowMapper doctorRowMapper;
	@Autowired
	private DoctorRatingRowMapper doctorRatingRowMapper;
	@Autowired
	private DepartmentRowMapper departmentRowMapper;
	@Autowired
	private DoctorScheduleRowMapper doctorScheduleRowMapper;
	
	public List<Integer> selectUnavailableTimes(int doctorNo) {
		String query = "select * from treatment_tbl where appoint_date = to_char(sysdate, 'yyyy-MM-dd') and doctor_no = ?";
		Object[] params = {doctorNo};
		List<Treat> list = jdbc.query(query, treatRowMapper, params);
		
		List<Integer> unavailableTimes = new ArrayList<>();
		for(Treat t : list) {
			unavailableTimes.add(t.getAppointTime());
		}
		return unavailableTimes;
	}

	public List<Doctor> selectDoctorsWithRating(int departmentNo) {
		String query = "SELECT\r\n"
				+ "	    doctor.*,\r\n"
				+ "	    department_name,\r\n"
				+ "	    (SELECT COUNT(review_star) FROM review WHERE doctor_no = doctor.doctor_no) AS review_total,\r\n"
				+ "	    nvl((SELECT AVG(review_star) FROM review WHERE doctor_no = doctor.doctor_no),0.0) AS review_rating\r\n"
				+ "	    FROM doctor_tbl doctor\r\n"
				+ "	    JOIN department_tbl dept ON (doctor.department_no = dept.DEPARTMENT_NO)"
				+ "		WHERE dept.department_no = ?";
		Object[] params = {departmentNo};
		List<Doctor> list = jdbc.query(query, doctorRatingRowMapper, params);
		return list;
	}

	public Doctor selectOneDoctor(int doctorNo) {
		String query = "select * from doctor_tbl where doctor_no = ?";
		Object[] params = {doctorNo};
		List list = jdbc.query(query, doctorRowMapper, params);
		return (Doctor) list.get(0);
	}

	public int insertTreatment(Treat t) {
		String query = "insert into treatment_tbl values(treatment_seq.nextval, to_char(sysdate, 'yyyy-MM-dd'), ?, 0, 0, null, null, ?, ?)";
		Object[] params = {t.getAppointTime(), t.getMemberNo(), t.getDoctorNo()};
		int result = jdbc.update(query, params);
		return result;
	}

	public Department selectDepartment(int departmentNo) {
		String query = "select * from department_tbl where department_no = ?";
		Object[] params = {departmentNo};
		List list = jdbc.query(query, departmentRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			return (Department) list.get(0);
		}
	}

	public List selectAllDepartment() {
		String query = "select * from department_tbl order by 1";
		List list = jdbc.query(query, departmentRowMapper);
		return list;
	}

	public List selectDoctorsWithSchedule(int departmentNo) {
		String query = "select doctor.doctor_no, appoint_time from doctor_tbl doctor\r\n"
					+ "join treatment_tbl treat on (doctor.doctor_no = treat.doctor_no)\r\n"
					+ "where treat.appoint_date = to_char(sysdate, 'yyyy-MM-dd') and doctor.department_no = ?";
		Object[] params = {departmentNo};
		List list = jdbc.query(query, doctorScheduleRowMapper, params);
		return list;
	}

}
