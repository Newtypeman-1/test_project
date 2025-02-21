package kr.co.iei.doctor.controller;


import java.util.List;

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
import kr.co.iei.doctor.model.service.DoctorService;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.doctor.model.vo.DoctorPageList;
import kr.co.iei.review.model.service.ReviewService;
import kr.co.iei.treat.model.vo.Treat;
import kr.co.iei.util.EmailSender;

@Controller
@RequestMapping(value="/doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private EmailSender emailSender;
	
	@PostMapping(value="/login")
	public String doctorLogin(Doctor d, HttpSession session) {
		Doctor doctor = doctorService.selectOneDoctor(d);
		if(doctor == null) {			
			return "redirect:/";
		}else {
			session.setAttribute("doctor", doctor);
			return "redirect:/";
		}
	}
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping(value="/mypage")
	public String doctorMypage(@SessionAttribute Doctor doctor, Model model) {
		String doctorId = doctor.getDoctorId();
		Doctor d = doctorService.selectOneDoctor(doctor);
		model.addAttribute("doctor", d);
		return "doctor/mypage";
	}
	@PostMapping(value="/update")
	public String update(Doctor d, @SessionAttribute Doctor doctor) {
		int doctorNo = doctor.getDoctorNo();
		d.setDoctorNo(doctorNo);
		int result = doctorService.updateDoctor(d);
		if(result > 0) {
			doctor.setDoctorPw(d.getDoctorPw());
			doctor.setDoctorPhone(d.getDoctorPhone());
			doctor.setDoctorEmail(d.getDoctorEmail());
			return "redirect:/doctor/mypage";
		}else {
			return "redirect:/";
		}
	}
	@GetMapping(value="/qna")
	public String doctorQna(Model model, @SessionAttribute(required = false) Doctor doctor) {
		List allReview = reviewService.allReview(doctor);
		model.addAttribute("reviewList", allReview);
		List allBoard = boardService.allBoard(doctor);
		model.addAttribute("boardList", allBoard);
		return "doctor/qna";
	}
	
	@GetMapping(value="/findIdPwFrm")
	public String findIdPwFrm() {
		return "doctor/findIdPwFrm";
	}
	
	@ResponseBody
	@PostMapping(value="/findId")
	public int findId(Doctor d, Model model) {
		Doctor doctor = doctorService.findId(d);
		int r = 0;
		if(doctor == null) {
			return r;
		}else {
			String emailTitle = "HelpDOC 요청하신 아이디 찾기 조회한 결과입니다.";
			String emailContent = "<h1>안녕하십니까 HelpDOC 입니다.</h1>"
									+"<h3>귀하의 아이디는"
									+"[<span style='color: red;'>"
									+doctor.getDoctorId()
									+"</span>]"
									+"입니다.</h3>";
			emailSender.sendMail(emailTitle, d.getDoctorEmail(), emailContent);
			r = 1;
			return r;
		}
	}
	
	@ResponseBody
	@PostMapping(value="/findPw")
	public int findPw(Doctor d, Model model) {
		Doctor doctor = doctorService.findPw(d);
		int r = 0;
		if(doctor == null) {
			return r;
		}else {
			String emailTitle = "HelpDOC 요청하신 비밀번호 찾기 조회한 결과입니다.";
			String emailContent = "<h1>안녕하십니까 HelpDOC 입니다.</h1>"
									+"<h3>귀하의 비밀번호는"
									+"[<span style='color: red;'>"
									+doctor.getDoctorPw()
									+"</span>]"
									+"입니다.</h3>";
			emailSender.sendMail(emailTitle, d.getDoctorEmail(), emailContent);
			r = 1;
			return r;
		}
	}
	
	@GetMapping(value="/myMedicalRecordsPageFrm")
	public String myMedicalRecordsPageFrm(@SessionAttribute(required = false) Doctor doctor, int reqPage, Model model) {
		DoctorPageList dpl = doctorService.allMedicalRecords(doctor, reqPage);
		model.addAttribute("list", dpl.getList());
		model.addAttribute("pageNavi", dpl.getPageNavi());
		return "doctor/myMedicalRecordsPage";
	}
	
	@GetMapping(value="/myOpinion")
	public String myOpinion(int treatmentNo, int doctorNo, Model model) {
		Treat t = doctorService.selectOpinion(treatmentNo, doctorNo);
		model.addAttribute("t", t);
		return "doctor/myOpinion";
	}
	
	@ResponseBody
	@PostMapping(value="/opinionSubmit")
	public String opinionSubmit(Treat t, @SessionAttribute(required = false) Doctor doctor, Model model) {
		int r = doctorService.updateOpinion(t, doctor);
		if(r != 0) {
			model.addAttribute("title","소견서 작성 완료");
			model.addAttribute("text","소견서 작성이 완료되었습니다.");
			model.addAttribute("icon","success");
			model.addAttribute("loc", "/doctor/myOpinion?treatment_no="+t.getTreatmentNo());
			return "common/msg";
		}else {
			model.addAttribute("title","소견서 작성 실패");
			model.addAttribute("text","소견서 작성 실패.");
			model.addAttribute("icon","warning");
			model.addAttribute("loc", "/doctor/myOpinion?treatment_no="+t.getTreatmentNo());
			return "common/msg";
		}
	}
}
