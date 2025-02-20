package kr.co.iei.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.admin.model.service.AdminService;
import kr.co.iei.doctor.model.service.DoctorService;
import kr.co.iei.doctor.model.vo.Doctor;
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
	@Value(value="${file.root}")
	private String root;
	
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
	
	@GetMapping(value="/addAcount")
	public String addAcount() {
		return "admin/addAcount";
		
	}
	
	@PostMapping(value="/addAcount")
	public String join(Doctor d, Model model, MultipartFile imgFile) {
		int result = adminService.insertDoctor(d);
		model.addAttribute("title", "회원가입 성공");
		model.addAttribute("text","회원가입에 성공했습니다.");
		model.addAttribute("icon","success");
		model.addAttribute("loc", "/admin/mainPage");
		return "common/msg";
	}

	
}













