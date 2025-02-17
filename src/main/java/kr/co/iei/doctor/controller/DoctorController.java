package kr.co.iei.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.doctor.model.service.DoctorService;

@Controller
@RequestMapping(value="/doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
}
