package kr.co.iei.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentRowMapper3 implements RowMapper<Comment>{

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment c = new Comment();
		c.setCommentNo(rs.getInt("comment_no"));
		c.setCommentContent(rs.getString("comment_content"));
		c.setCommentDate(rs.getString("comment_date"));
		c.setDoctorNo(rs.getInt("doctor_no"));
		c.setDoctorImg(rs.getString("doctor_img"));
		c.setDoctorName(rs.getString("doctor_name"));
		c.setDepartmentName(rs.getString("department_name"));
		return c;
	}

}
