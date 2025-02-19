package kr.co.iei.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.board.model.service.BoardService;
import kr.co.iei.doctor.model.service.DoctorService;
import kr.co.iei.member.model.service.MemberService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping(value="/boardList")
	public String boardList(Model model, int reqPage) {
		List list = boardService.selectBoardList(reqPage);
		model.addAttribute("list", list);
		return "board/boardList";
		
	}

}








