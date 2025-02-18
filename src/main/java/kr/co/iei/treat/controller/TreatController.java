package kr.co.iei.treat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.treat.model.service.TreatService;

@Controller
@RequestMapping(value="/treat")
public class TreatController {

	@Autowired
	private TreatService treatService;
	
	@GetMapping(value="/appointFrm")
	public String getAvailableTimes(int doctorNo, Model mod) {
		List<Integer> unavailableTimes = treatService.selectUnavailableTimes(doctorNo);
		Doctor doctor = treatService.selectOneDoctor(doctorNo);
		mod.addAttribute("unavailableTimes", unavailableTimes);
		mod.addAttribute("doctor", doctor);
		System.out.println(unavailableTimes);
		return "treat/appointFrm";
	}
	
	@GetMapping(value="/depart1")
	public String depart1(Model mod) {
		int departmentNo = 1;
		List<Doctor> list = treatService.selectDoctors(departmentNo);
		mod.addAttribute("list", list);
		return "treat/depart1";
	}
}
