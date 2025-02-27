package kr.co.iei.member.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import kr.co.iei.board.model.service.BoardService;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.model.vo.MemberPageList;
import kr.co.iei.review.model.service.ReviewService;
import kr.co.iei.treat.model.vo.Treat;
import kr.co.iei.util.EmailSender;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/loginFrm")
	public String loginFrm() {
		return "member/loginFrm";
	}
	
	@PostMapping(value="/login")
	public String login(Member m, Model model, HttpSession session) {
		Member member = memberService.selectOneMember(m);
		if(member == null) {
			model.addAttribute("title","로그인 실패");
			model.addAttribute("text","아이디 또는 비밀번호를 확인하세요.");
			model.addAttribute("icon","error");
			model.addAttribute("loc", "/member/loginFrm");
			return "common/msg";
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
	
	@GetMapping(value="/registerFrm")
	public String registerFrm() {
		return "member/register";
	}
	
	@PostMapping(value="/register")
	public String register(Member m, Model model, String email, String com1) {
		String memberEmail = email+"@"+com1;
		int r = memberService.registerMember(m, memberEmail);
		System.out.println(m.toString());
		model.addAttribute("title","회원가입 성공");
		model.addAttribute("text","회원가입에 성공했습니다.");
		model.addAttribute("icon","success");
		model.addAttribute("loc", "/member/loginFrm");
		return "common/msg";
	}
	
	@ResponseBody
	@GetMapping(value="idCheck")
	public int idCheck(Member m) {
		Member member = memberService.idCheck(m);
		int r = 0;
		if(member == null) {
			r = 1;
		}
		return r;
	}
	
	@PostMapping(value="/sendMail")
	public String sendMail(String emailTitle, String receiver, String emailContent) {
		System.out.println("제목 : "+emailTitle);
		System.out.println("받는사람 : "+receiver);
		System.out.println("내용 : "+emailContent);
		
		emailSender.sendMail(emailTitle,receiver,emailContent);
		return "redirect:/api/email";
	}
	
	@ResponseBody
	@GetMapping(value="/sendCode")
	public String sendCode(String memberEmail) {
		String emailTitle = "HelpDOC 인증메일 입니다.";
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<6; i++) {
			
			int flag = r.nextInt(3);
			
			if(flag == 0) {
				int randomCode = r.nextInt(10);
				sb.append(randomCode);
			}else if(flag == 1) {
				char randomCode = (char)(r.nextInt(26)+65);
				sb.append(randomCode);
			}else {
				char randomCode = (char)(r.nextInt(26)+97);
				sb.append(randomCode);
			}
		}
		String emailContent = "<h1>안녕하십니까 HelpDOC 입니다.</h1>"
								+"<h3>귀하의 인증번호는"
								+"[<span style='color: red;'>"
								+sb.toString()
								+"</span>]"
								+"입니다.</h3>";
		emailSender.sendMail(emailTitle, memberEmail, emailContent);
		return sb.toString();
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
	public String memberQna(Model model, @SessionAttribute(required = false) Member member) {
		List memberAllReview = reviewService.memberAllReview(member);
		model.addAttribute("reviewList", memberAllReview);	
		List memberAllBoard = boardService.memberAllBoard(member);
		model.addAttribute("boardList", memberAllBoard);
		return "member/qna";
	}	
	
	@GetMapping(value="/findIdPwFrm")
	public String findIdPwFrm() {
		return "member/findIdPwFrm";
	}
	
	@ResponseBody
	@PostMapping(value="/findId")
	public int findId(Member m, Model model) {
		Member member = memberService.findId(m);
		int r = 0;
		if(member == null) {
			return r;
		}else {
			String emailTitle = "HelpDOC 요청하신 아이디 찾기 조회한 결과입니다.";
			String emailContent = "<h1>안녕하십니까 HelpDOC 입니다.</h1>"
									+"<h3>귀하의 아이디는"
									+"[<span style='color: red;'>"
									+member.getMemberId()
									+"</span>]"
									+"입니다.</h3>";
			emailSender.sendMail(emailTitle, m.getMemberEmail(), emailContent);
			r = 1;
			return r;
		}
	}
	
	@ResponseBody
	@PostMapping(value="/findPw")
	public int findPw(Member m, Model model) {
		Member member = memberService.findPw(m);
		int r = 0;
		if(member == null) {
			return r;
		}else {
			String emailTitle = "HelpDOC 요청하신 비밀번호 찾기 조회한 결과입니다.";
			String emailContent = "<h1>안녕하십니까 HelpDOC 입니다.</h1>"
									+"<h3>귀하의 비밀번호는"
									+"[<span style='color: red;'>"
									+member.getMemberPw()
									+"</span>]"
									+"입니다.</h3>";
			emailSender.sendMail(emailTitle, m.getMemberEmail(), emailContent);
			r = 1;
			return r;
		}
	}
	
	@GetMapping(value="/myMedicalRecordsPageFrm")
	public String myMedicalRecordsPageFrm(@SessionAttribute(required = false) Member member, int reqPage, Model model) {
		MemberPageList mpl = memberService.allMedicalRecords(member, reqPage);
		model.addAttribute("list", mpl.getList());
		model.addAttribute("pageNavi", mpl.getPageNavi());
		return "member/myMedicalRecordsPage";
	}
	
	@GetMapping(value="/delete")
	public String deleteMember(@SessionAttribute Member member, Model model) {
		int memberNo = member.getMemberNo();
		int result = memberService.deleteMemeber(memberNo);
		model.addAttribute("title","탈퇴 완료");
		model.addAttribute("text", "회웥 탈퇴가 완료되었습니다.");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/member/logout");
		return "common/msg";
	}
	
	@GetMapping(value="/myOpinion")
	public String myOpinion(int treatmentNo, int memberNo, Model model) {
		Treat t = memberService.selectOpinion(treatmentNo, memberNo);
		model.addAttribute("t", t);
		return "member/myOpinion";
	}
	
	@RequestMapping(value="/loginMsg")
	public String loginMsg(Model model) {
		model.addAttribute("title","로그인 필요");
		model.addAttribute("text", "로그인을 하고 해야지");
		model.addAttribute("icon", "warning");
		model.addAttribute("loc", "/member/loginFrm");
		return "common/msg";
	}
}
