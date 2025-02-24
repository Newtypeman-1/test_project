package kr.co.iei.board.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.board.model.vo.Board;
import kr.co.iei.board.model.vo.BoardRowMapper;
import kr.co.iei.comment.model.vo.Comment;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.vo.Member;

@Repository
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private BoardRowMapper boardRowMapper;
	
	public int selectBoardTotalCount() {
		String query = "select count(*) from board";
		return jdbc.queryForObject(query, Integer.class);
	}

	public List selectBoardList(int start, int end) {
		String query = "select * from (select rownum as rnum, b.* from (select * from board order by 1 desc) b) where rnum between ? and ?";
		Object[] params = {start, end};
		List list = jdbc.query(query, boardRowMapper, params);
		return list;
	}

	public List memberAllBoard(Member member) {
		String query = "select * from (select rownum as rnum, o.* from (select b.* from board b where board_writer = ? order by board_no desc)o) where rnum between 1 and 5";
		Object[] params = {member.getMemberId()};
		List memberAllBoard = jdbc.query(query, boardRowMapper, params);
		return memberAllBoard;
	}

	public int insertBoard(Board b, Member member) {
		String query = "insert into board values(board_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),0)";
		Object[] params = {b.getBoardTitle(), member.getMemberId(), b.getBoardContent()};
		int result = jdbc.update(query, params);
		return result;
	}

	public Board selectBoard(int boardNo) {
		String query = "select * from board where board_no = ?";
		Object[] params = {boardNo};
		 List list = jdbc.query(query, boardRowMapper, params);
		 if(list.isEmpty()) {
			 return null;
		 }else {
			 Board b = (Board)list.get(0);			 
			 return b;
		 }
	}

	public int writeComment(Comment c) {
		String query = "insert into comment_tbl values(comment_tbl_seq.nextval,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		Object[] params = {c.getCommentContent(), c.getDoctorNo(), c.getBoardNo()};
		int result = jdbc.update(query, params);
		return result;
	}
}