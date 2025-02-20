package kr.co.iei.board.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.board.model.vo.BoardRowMapper;

@Repository
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private BoardRowMapper boardRowMapper;
	
	public List selectBoardList(int start, int end) {
		String query = "select * from (select rownum as rnum, b.* from (select * from board order by board_no desc)b) where rnum between ? and ?";
		Object[] params = {start,end};
		List list = jdbc.query(query, boardRowMapper,params);
		return list;
	}

	public int selectBoardTotalCount() {
		String query = "select count(*) from board";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

}
