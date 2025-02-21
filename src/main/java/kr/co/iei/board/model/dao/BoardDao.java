package kr.co.iei.board.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.board.model.vo.BoardRowMapper;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.vo.Member;

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

	public List allBoard(Doctor doctor) {
		String query = "select * from (select rownum as rnum, h.* from(select r.*,(select doctor_name from doctor_tbl where doctor_no = r.doctor_no) doctor_name from comment_tbl r where doctor_no = ? order by 1 desc)h) where rnum between 1 and 5;";
		Object[] params = {doctor.getDoctorNo()};
		List allBoard = jdbc.query(query, boardRowMapper,params);
		return allBoard;
	}

	public List memberAllBoard(Member member) {
		String query = "select * from (select rownum as rnum, o.* from (select b.* from board b where board_writer = ? order by board_no desc)o) where rnum between 1 and 5";
		Object[] params = {member.getMemberId()};
		List memberAllBoard = jdbc.query(query, boardRowMapper, params);
		return memberAllBoard;
	}

}
