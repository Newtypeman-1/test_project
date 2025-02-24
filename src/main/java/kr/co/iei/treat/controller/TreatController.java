package kr.co.iei.treat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.treat.model.service.TreatService;
import kr.co.iei.treat.model.vo.Department;
import kr.co.iei.treat.model.vo.Treat;

@Controller
@RequestMapping(value="/treat")
public class TreatController {

	@Autowired
	private TreatService treatService;
	
	@GetMapping(value="/allDepart")
	public String allDepart(Model mod) {
		List list = treatService.selectAllDepartment();
		mod.addAttribute("departments", list);
		return "treat/allDepart";
	}
	
	@GetMapping(value="/department")
	public String department(Model mod, int departmentNo) {
		List<Doctor> doctorsWithRating = treatService.selectDoctorsWithRating(departmentNo);
		Department department = treatService.selectDepartment(departmentNo);
		
		mod.addAttribute("doctorsWithRating", doctorsWithRating);
		mod.addAttribute("department", department);
		return "treat/department";
	}
	
	@ResponseBody
	@GetMapping(value="/schedule")
	public List schedule(int departmentNo, Model mod) {
		List<Doctor> schedule = treatService.selectDoctorsWithSchedule(departmentNo);
		mod.addAttribute("schedule", schedule);
		return schedule;
	}

	@GetMapping(value="/appointFrm")
	public String appointFrm(int doctorNo, Model mod) {
		Doctor doctor = treatService.selectOneDoctor(doctorNo);
		List<Integer> unavailableTimes = treatService.selectUnavailableTimes(doctorNo);
		mod.addAttribute("doctor", doctor);
		mod.addAttribute("unavailableTimes", unavailableTimes);
		System.out.println(doctor);
		return "treat/appointFrm";
	}
	
	@PostMapping(value="/appoint")
	public String appoint(Treat t, Model mod) {
		int result = treatService.insertTreatment(t);
		if(result == 1) {
			mod.addAttribute("title", "예약 완료");
			mod.addAttribute("text", "진료가 예약되었습니다.");
			mod.addAttribute("icon", "success");
			mod.addAttribute("loc", "/member/mypage");
			return "common/msg";
		}else {
			mod.addAttribute("title", "알 수 없는 오류");
			mod.addAttribute("text", "메인 페이지로 이동합니다.");
			mod.addAttribute("icon", "error");
			mod.addAttribute("loc", "/");
			return "common/msg";
		}
	}
}
