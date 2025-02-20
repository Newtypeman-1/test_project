package kr.co.iei.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.board.model.dao.BoardDao;

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
		
		//int pageNaviSize = 5;
		//int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
	}
}
