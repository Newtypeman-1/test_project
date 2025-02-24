package kr.co.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.admin.model.vo.AdminRowMapper;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.treat.model.vo.TreatRowMapper2;
import kr.co.iei.treat.model.vo.TreatRowMapper3;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private AdminRowMapper adminRowMapper;
	@Autowired
	private TreatRowMapper3 treatRowMapper3;

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

	public List allSchedule() {
		String query = "select * from (select rownum as rnum, z.* from (select * from treatment_tbl t join doctor_tbl d on t.doctor_no = d.doctor_no join member_tbl m on t.member_no = m.member_no join department_tbl p on d.department_no = p.department_no order by 1 desc) z)";
		List list = jdbc.query(query, treatRowMapper3);
		return list;
	}

}
