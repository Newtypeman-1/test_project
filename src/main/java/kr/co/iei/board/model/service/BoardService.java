package kr.co.iei.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.board.model.dao.BoardDao;
import kr.co.iei.board.model.vo.Board;

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
}
