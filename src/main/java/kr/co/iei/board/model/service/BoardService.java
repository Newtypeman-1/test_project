package kr.co.iei.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.board.model.dao.BoardDao;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.vo.Member;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public List selectBoardList(int reqPage) {
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		List list = boardDao.selectBoardList(start, end);
		int totalCount = boardDao.selectBoardTotalCount();
		int totalPage = totalCount/numPerPage;
		if(totalCount%numPerPage != 0) {
			totalPage += 1;
		}
		return list;
	}

	public List allBoard(Doctor doctor) {
		List allBoard = boardDao.allBoard(doctor);
		return allBoard;
	}

	public List memberAllBoard(Member member) {
		List memberAllBoard = boardDao.memberAllBoard(member);
		return memberAllBoard;
	}
}
