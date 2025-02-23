package kr.co.iei.comment.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.comment.model.vo.CommentRowMapper;
import kr.co.iei.doctor.model.vo.Doctor;

@Repository
public class CommentDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private CommentRowMapper commentRowMapper;
	public List allComment(Doctor doctor) {
		String query = "select * from (select rownum as rnum, h.* from(select r.*,(select doctor_name from doctor_tbl where doctor_no = r.doctor_no) doctor_name from comment_tbl r where doctor_no = ? order by 1 desc)h) where rnum between 1 and 5";
		Object[] params = {doctor.getDoctorNo()};
		List allComment = jdbc.query(query, commentRowMapper, params);
		return allComment;
	}
}
