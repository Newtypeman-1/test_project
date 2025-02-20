package kr.co.iei.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.admin.model.service.AdminService;
import kr.co.iei.doctor.model.service.DoctorService;
import kr.co.iei.member.model.service.MemberService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping(value="/mainPage")
	public String mainPage() {
		return "admin/mainPage";
	}	
		
	@GetMapping(value="/allMember")
	public String allMember(Model model) {
		List list = memberService.selectAllMember();
		List list2 = doctorService.selectAllDoctor();
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		return "admin/allMember";
	}
	
}
