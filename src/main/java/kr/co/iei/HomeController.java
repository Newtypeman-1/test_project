package kr.co.iei;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.board.model.service.BoardService;
import kr.co.iei.board.model.vo.Board;
import kr.co.iei.review.model.service.ReviewService;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.treat.model.service.TreatService;
import kr.co.iei.treat.model.vo.Symptom;

@Controller
public class HomeController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private TreatService treatService;
	
	@RequestMapping(value="/")
	public String home(Model mod) {
		List<Board> boardList = boardService.selectRecentBoardList();
		List<Review> reviewList = reviewService.selectRecentReviewList();
		List<Symptom> symptomList = treatService.selectAllSymptoms();
		
		mod.addAttribute("boardList", boardList);
		mod.addAttribute("reviewList", reviewList);
		mod.addAttribute("symptomList", symptomList);
		return "index";
	}
	
}
