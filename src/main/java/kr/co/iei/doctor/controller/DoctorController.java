package kr.co.iei.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import kr.co.iei.doctor.model.service.DoctorService;
import kr.co.iei.doctor.model.vo.Doctor;

@Controller
@RequestMapping(value="/doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	
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
	@GetMapping(value="logout")
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
	public String doctorQna() {
		return "/doctor/qna";
	}
}
