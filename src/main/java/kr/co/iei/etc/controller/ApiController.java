package kr.co.iei.etc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/etc")
public class ApiController {
	
	@GetMapping(value="/map")
	public String map() {
		return "etc/map";
	}
}