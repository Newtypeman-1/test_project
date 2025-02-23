package kr.co.iei.doctor.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorScheduleRowMapper implements RowMapper<Doctor>{

	@Override
	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor d = new Doctor();
		d.setDoctorNo(rs.getInt("doctor_no"));
		d.setAppointTime(rs.getInt("appoint_time"));
		return d;
	}

}
