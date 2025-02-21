package kr.co.iei.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.review.model.dao.ReviewDao;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.review.model.vo.ReviewListData;

@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewDao;

	public ReviewListData reviewAllList(int reqPage) {
		int numPage = 10;
		int end = reqPage * numPage;
		int start = end - numPage + 1;
		List list = reviewDao.reviewAllList(start, end);
		
		int totalReview = reviewDao.totalReview();
		int totalPage = totalReview/numPage;
		if(totalReview%numPage != 0) {
			totalPage += 1;
		}
		int naviSize = 5;
		int naviNo = ((reqPage - 1)/naviSize) * naviSize + 1;
		String navi = "<ul class='pagination justify-content-center'>";
		navi += "<li class='page-item'>";
		navi += "<a class='page-link' href='/review/list?reqPage="+(naviNo-1)+"' aria-label='Previous'>";
		navi += "<span aria-hidden='true'>&laquo;</span>";
		navi += "</a></li>";
		
		for(int i=0;i<naviSize;i++) {
			navi += "<li class='page-item'>";
			navi += "<a class='page-link' href='/review/list?reqPage="+naviNo+"'>";				
			navi += naviNo;
			navi += "</a></li>";
			naviNo++;			
			if(naviNo > totalPage) {
				break;
			}			
		}
		navi += "<li class='page-item'>";
		navi += "<a class='page-link' href='/review/list?reqPage="+naviNo+"' aria-label='Next'>";
		navi += "<span aria-hidden='true'>&raquo;</span>";
		navi += "</a></li>";
		navi += "</ul>";
		ReviewListData rld = new ReviewListData(list, navi);
		return rld;
	}

	public List allReview(Doctor doctor) {
		List allReview = reviewDao.allReview(doctor);
		return allReview;
	}

	public List memberAllReview(Member member) {
		List memberAllReview = reviewDao.memberAllReview(member);
		return memberAllReview;
	}

	@Transactional
	public int reviewWrite(Review r) {
		int reviewWrite = reviewDao.reviewWrite(r);
		return reviewWrite;
	}
}
