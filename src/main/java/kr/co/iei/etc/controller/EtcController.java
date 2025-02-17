package kr.co.iei.etc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;

@Controller
@RequestMapping(value="/etc")
public class EtcController {
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@PostMapping(value="ajaxCheckId")
	public int ajaxCheckId(Member m) {
		int r = memberService.ajaxCheckLogin(m);
		return r;
	}
}
