package kr.co.iei.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.board.model.dao.BoardDao;
import kr.co.iei.board.model.vo.Board;
import kr.co.iei.board.model.vo.Comment;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.vo.Member;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public int selectBoardTotalCount() {
		return boardDao.selectBoardTotalCount();
	}

	public List<Board> selectBoardList(int start, int amount) {
		int end = start + amount - 1;
		List list = boardDao.selectBoardList(start, end);
		return list;
	}

	public List memberAllBoard(Member member) {
		List list = boardDao.memberAllBoard(member);
		return list;
	}
	
	@Transactional
	public int insertBoard(Board b, Member member) {
		int result = boardDao.insertBoard(b, member);
		return result;
	}
	
	@Transactional
	public Board selectBoard(int boardNo) {
		Board b = boardDao.selectBoard(boardNo);		
		return b;
	}
	
	@Transactional
	public int commentWrtie(Comment c, Doctor doctor, Board board) {
		int result = boardDao.commentWrite(c, doctor, board);
		if(result != 0) {
			int r = boardDao.boardIsDone(board);
			return result;
		}else {
			return result;
		}
	}
	
	public List allComment(Doctor doctor) {
		List allComment = boardDao.allComment(doctor);
		return allComment;
	}

	@Transactional
	public int deleteBoard(int boardNo) {
		int result = boardDao.deleteBoard(boardNo);
		return result;
	}

	public Comment selectComment(int boardNo) {
		Comment c = boardDao.selectComment(boardNo);
		return c;
	}

	public List<Board> selectRecentBoardList() {
		List list = boardDao.selectRecentBoardList();
		return list;
	}



}
