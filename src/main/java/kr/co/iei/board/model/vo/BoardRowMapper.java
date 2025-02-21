package kr.co.iei.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BoardRowMapper implements RowMapper<Board> {

	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		Board b = new Board();
		b.setBoardContent(rs.getString("board_content"));
		b.setBoardNo(rs.getInt("board_no"));
		b.setBoardReadCount(rs.getInt("board_read_count"));
		b.setBoardRegDate(rs.getString("board_reg_date"));
		b.setBoardTitle(rs.getString("board_title"));
		b.setBoardWriter(rs.getString("board_writer"));
		b.setIsDone(rs.getString("is_done"));
		return b;
	}

}
