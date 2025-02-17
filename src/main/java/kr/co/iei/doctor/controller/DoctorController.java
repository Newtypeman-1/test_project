package kr.co.iei.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
			
			return "/doctor/loginFrm";
		}else {
			session.setAttribute("doctor", doctor);
			return "redirect:/";
		}
	}
}
