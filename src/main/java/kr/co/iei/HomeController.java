package kr.co.iei;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class HomeController {
	
	public String home() {
		return "index";
	}
}
