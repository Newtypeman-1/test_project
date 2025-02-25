package kr.co.iei.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.board.model.dao.BoardDao;
import kr.co.iei.board.model.vo.Board;
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

	public Board selectBoard(int boardNo) {
		Board b = boardDao.selectBoard(boardNo);
		return b;
	}

	public List<Board> selectRecentBoardList() {
		List list = boardDao.selectRecentBoardList();
		return list;
	}

}
