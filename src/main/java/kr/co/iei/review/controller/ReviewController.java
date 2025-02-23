package kr.co.iei.review.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.iei.doctor.model.service.DoctorService;
import kr.co.iei.review.model.service.ReviewService;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.review.model.vo.ReviewListData;

@Controller
@RequestMapping(value="/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	
//	@GetMapping(value="/list")
//	public String reviewList(Model model, int reqPage) {
//		ReviewListData rld = reviewService.reviewAllList(reqPage);
//		model.addAttribute("list", rld.getList());
//		model.addAttribute("navi", rld.getNavi());
//		return "review/list";
//	}
	
	@GetMapping(value="/list")
	public String reviewList2(Model model, int reqPage, int doctorNo) {
		ReviewListData rld = reviewService.reviewAllList(reqPage, doctorNo);
		model.addAttribute("list", rld.getList());
		model.addAttribute("navi", rld.getNavi());
		return "review/list";
	}

	@GetMapping(value="/writeFrm")
	public String reviewWriteFrm() {
		return "review/writeFrm";
	}

	@PostMapping(value="/write")
	public String reviewWrite(Review r, Model model) {
		int reviewWrite = reviewService.reviewWrite(r);
		model.addAttribute("title", "작성 완료");
		model.addAttribute("text","리뷰 등록이 완료되었습니다.");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/member/qna");
		return "common/msg";
	}
}