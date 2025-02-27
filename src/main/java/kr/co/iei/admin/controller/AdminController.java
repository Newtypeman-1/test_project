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
import kr.co.iei.admin.model.vo.AdminPageList;
import kr.co.iei.doctor.model.service.DoctorService;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.doctor.model.vo.DoctorPageList;
import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.MemberPageList;
import kr.co.iei.treat.model.service.TreatService;
import kr.co.iei.util.FileUtils;

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
	@Autowired
	private FileUtils fileUtils;
	@Autowired
	private TreatService treatService;
	
	@GetMapping(value="/allMember")
	public String allMember(Model model,int memberNo, int doctorNo) {
		MemberPageList mpl = memberService.allMemberList(memberNo, doctorNo);
		DoctorPageList dpl = doctorService.allDoctorList(memberNo, doctorNo);
		model.addAttribute("list1", mpl.getList());
		model.addAttribute("navi1", mpl.getPageNavi());
		model.addAttribute("list2", dpl.getList());
		model.addAttribute("navi2", dpl.getPageNavi());
		return "admin/allMember";
	}
	
	@GetMapping(value="/addAcount")
	public String addAcount() {
		return "admin/addAcount";
		
	}
	
	@PostMapping(value="/addAcount")
	public String addAccount(Doctor d, Model model, MultipartFile imgFile) {
		if(!imgFile.isEmpty()) {
			String savepath = root+"/doctor/";
			String filepath = fileUtils.upload(savepath, imgFile);
			d.setDoctorImg(filepath);
		}
		int result = adminService.insertDoctor(d);
		model.addAttribute("title", "회원가입 성공");
		model.addAttribute("text","회원가입에 성공했습니다.");
		model.addAttribute("icon","success");
		model.addAttribute("loc", "/doctor/mypage");
		return "common/msg";
	}
	
	@PostMapping(value="delAcount")
	public String delAccount(Doctor d, Model model) {
		int r = adminService.deleteDoctor(d);
		if(r != 0) {
			model.addAttribute("title", "직원 해고 성공");
			model.addAttribute("text", "성공적으로 직원을 해고했습니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/");
			return "common/msg";
		}else {
			model.addAttribute("title", "직원 해고 실패");
			model.addAttribute("text", "애석하게도 직원을 해고하지 못했습니다.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/");
			return "common/msg";
		}
	}
	@GetMapping(value="allSchedule")
	public String allSchedule(Model model, int reqPage) {
		AdminPageList apl = adminService.allSchedule(reqPage);
		model.addAttribute("list", apl.getList());
		model.addAttribute("pageNavi", apl.getPageNavi());
		return "admin/allSchedule";
	}
	
	@RequestMapping(value="/adminMsg")
	public String adminMsg(Model model) {
		model.addAttribute("title","접근 제한");
		model.addAttribute("text", "ㄴㄴ");
		model.addAttribute("icon", "warning");
		model.addAttribute("loc", "/member/loginFrm");
		return "common/msg";
	}
	
}













