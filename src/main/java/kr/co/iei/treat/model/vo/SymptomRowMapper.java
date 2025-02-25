package kr.co.iei.treat.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SymptomRowMapper implements RowMapper<Symptom>{

	@Override
	public Symptom mapRow(ResultSet rs, int rowNum) throws SQLException {
		Symptom s = new Symptom();
		
		s.setSymptomNo(rs.getInt("symptom_no"));
		s.setSymptomName(rs.getString("symptom_name"));
		s.setDepartmentNo(rs.getInt("department_no"));
		
		s.setSymptomImg(
				rs.getString("symptom_img").replace("/", ", ")
				);
		
		return s;
	}
	
}
