package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import kr.co.iei.doctor.model.vo.Doctor;
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
		Member member = memberService.selectOneMember(m);
		if(member == null) {
			return "redirect:/";
		}else {
			session.setAttribute("member", member);
			return "redirect:/";
		}
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping(value="/register")
	public String register() {
		return "member/register";
	}
	
	@GetMapping(value="/mypage")
	public String memberMypage(@SessionAttribute Member member, Model model) {
		String memberId = member.getMemberId();
		Member m = memberService.selectOneMember(member);
		model.addAttribute("member", m);
		return "member/mypage";
	}
	
	@PostMapping(value="/update")
	public String update(Member m, @SessionAttribute Member member) {
		int memberNo = member.getMemberNo();
		m.setMemberNo(memberNo);
		int result = memberService.updateMember(m);
		if(result > 0) {
			member.setMemberPw(m.getMemberPw());
			member.setMemberPhone(m.getMemberPhone());
			member.setMemberEmail(m.getMemberEmail());
			return "redirect:/member/mypage";
		}else {
			return "redirect:/";
		}
	}
	@GetMapping(value="/qna")
	public String memberQna() {
		return "member/qna";
	}
}
