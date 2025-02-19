package kr.co.iei.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.review.model.service.ReviewService;
import kr.co.iei.review.model.vo.ReviewListData;

@Controller
@RequestMapping(value="/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(value="list")
	public String reviewList(Model model, int reqPage) {
		ReviewListData rld = reviewService.reviewAllList(reqPage);
		model.addAttribute("list", rld.getList());
		model.addAttribute("navi", rld.getNavi());
		return "review/list";
	}
	
}
