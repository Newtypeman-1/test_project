package kr.co.iei.treat.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TreatRowMapper implements RowMapper<Treat>{

	@Override
	public Treat mapRow(ResultSet rs, int rowNum) throws SQLException {
		Treat t = new Treat();
		t.setTreatmentNo(rs.getInt("treatment_no"));
		t.setAppointDate(rs.getString("appoint_date"));
		t.setAppointTime(rs.getInt("appoint_time"));
		t.setPayAmount(rs.getInt("pay_amount"));
		t.setIsDone(rs.getString("is_done"));
		t.setOpinion(rs.getString("opinion"));
		t.setMemberNo(rs.getInt("member_no"));
		t.setDoctorNo(rs.getInt("doctor_no"));
		return t;
	}

}
