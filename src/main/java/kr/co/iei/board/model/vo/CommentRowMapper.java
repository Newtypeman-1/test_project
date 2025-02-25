package kr.co.iei.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentRowMapper implements RowMapper<Comment>{

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment c = new Comment();
		c.setBoardNo(rs.getInt("board_no"));
		c.setCommentContent(rs.getString("comment_content"));
		c.setCommentDate(rs.getString("comment_date"));
		c.setCommentNo(rs.getInt("comment_no"));
		c.setDoctorNo(rs.getInt("doctor_no"));
		return c;
	}

}
