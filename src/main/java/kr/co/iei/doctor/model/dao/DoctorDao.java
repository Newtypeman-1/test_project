package kr.co.iei.doctor.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.doctor.model.vo.DoctorRowMapper;

@Repository
public class DoctorDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private DoctorRowMapper doctorRowMapper;
}
