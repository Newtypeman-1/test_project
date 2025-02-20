package kr.co.iei.treat.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentRowMapper implements RowMapper<Department>{

	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department dp = new Department();
		dp.setDepartmentNo(rs.getInt("department_no"));
		dp.setDepartmentName(rs.getString("department_name"));
		return dp;
	}
	
}
