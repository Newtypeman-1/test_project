package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value="/loginFrm")
	public String loginFrm() {
		return "member/loginFrm";
	}
	
	@PostMapping(value="/login")
	public String login(Member m, Model model, HttpSession session) {
		/* Member member = memberService.selectOneMember(m); */
		return "redirect:/";
	}
	@GetMapping(value="/register")
	public String register() {
		return "member/register";
	}
}
