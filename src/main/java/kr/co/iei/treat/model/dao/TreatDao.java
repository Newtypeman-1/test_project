package kr.co.iei.treat.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.doctor.model.vo.DoctorRowMapper;
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
	private DepartmentRowMapper departmentRowMapper;
	
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

	public List<Doctor> selectDoctors(int departmentNo) {
		String query = "select * from doctor_tbl where department_no = ?";
		Object[] params = {departmentNo};
		List<Doctor> list = jdbc.query(query, doctorRowMapper, params);
		
		//임시
//		String query = "select * from doctor_tbl";
//		List<Doctor> list = jdbc.query(query, doctorRowMapper);
		
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

	public String selectDepartmentName(int departmentNo) {
		String query = "select department_name from department_tbl where department_no = ?";
		Object[] params = {departmentNo};
		try {
			String result = jdbc.queryForObject(query, params, String.class);
			return result;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List selectAllDepartment() {
		String query = "select * from department_tbl order by 1";
		List list = jdbc.query(query, departmentRowMapper);
		return list;
	}

}
